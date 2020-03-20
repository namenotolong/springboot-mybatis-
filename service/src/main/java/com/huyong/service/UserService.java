package com.huyong.service;

import com.huyong.constant.AuthCheckConstant;
import com.huyong.constant.CommonConstant;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.module.UserBO;
import com.huyong.enums.RoleEnum;
import com.huyong.enums.StatusEnum;
import com.huyong.exception.AuthException;
import com.huyong.utils.AuthUtils;
import com.huyong.utils.JwtHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: UserService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class UserService {
    private static final String EMAIL_REX = "^\\w+((.\\w+)|(-\\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$";

    @Resource
    private UserMapper userMapper;
    @Resource
    private EmailAdviceService emailAdviceService;

    public UserDO convertBo2Do(UserBO user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        return userDO;
    }
    public List<UserDO> convertBos2Dos(List<UserBO> users) {
        if (null == users || users.isEmpty()) {
            return new ArrayList<>();
        }
        return users.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public UserBO convertDo2Bo(UserDO user) {
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(user, userBO);
        return userBO;
    }
    public List<UserBO> convertDos2Bos(List<UserDO> users) {
        if (null == users || users.isEmpty()) {
            return new ArrayList<>();
        }
        return users.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }
    public UserDO modifyUserDO() {
        UserDO userDO = new UserDO();
        userDO.setStatus(StatusEnum.PRESENT.getCode());
        return userDO;
    }
    public UserDO modifyUserDO(UserDO userDO) {
        userDO.setStatus(StatusEnum.PRESENT.getCode());
        return userDO;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    public UserBO login(UserBO user) {
        final UserDO userDO = modifyUserDO(convertBo2Do(user));
        //判断登录账号是邮箱还是用户名
        final String account = user.getAccount();
        if (account.matches(EMAIL_REX)) {
            userDO.setEmail(account);
        } else {
            userDO.setUserName(account);
        }
        final List<UserDO> userDOS = userMapper.queryByCondition(userDO);
        if (CollectionUtils.isEmpty(userDOS)) {
            throw new AuthException("账户或密码错误！");
        }
        final UserDO customer = userDOS.get(0);
        user = convertDo2Bo(customer);
        String token = JwtHelper.createJWT(customer.getId(), customer.getUserName(), customer.getEmail(), customer.getRole());
        user.setToken(AuthCheckConstant.START + token);
        return user;
    }

    /**
     * 获取当前用户
     * @return
     */
    public UserBO getUser() {
        return AuthUtils.getUser();
    }

    /**
     * 注册用户
     * @param userBO
     */
    public void register(UserBO userBO) {
        emailAdviceService.checkCode(userBO.getEmail(), userBO.getCode());
        checkUserNameUnique(userBO.getUserName());
        checkEmailUnique(userBO.getEmail());
        UserDO userDO = convertBo2Do(userBO);
        userDO.setRole(RoleEnum.CUSTOMER.getCode());
        userDO.setStatus(StatusEnum.PRESENT.getCode());
        userDO.setPicture(CommonConstant.DEFAULT_BAR_IMG);
        userMapper.insert(userDO);
    }

    /**
     * 验证用户名唯一性
     * @param userName
     */
    private boolean checkUserNameUnique(String userName) {
        UserDO userDO = modifyUserDO();
        //验证用户名唯一性
        userDO.setUserName(userName);
        List<UserDO> userDOS = userMapper.queryByCondition(userDO);
        return CollectionUtils.isEmpty(userDOS);
    }
    /**
     * 验证邮箱唯一性
     * @param email
     */
    private boolean checkEmailUnique(String email) {
        UserDO userDO = modifyUserDO();
        userDO.setStatus(StatusEnum.PRESENT.getCode());
        //验证用户名唯一性
        userDO.setEmail(email);
        List<UserDO> userDOS = userMapper.queryByCondition(userDO);
        return CollectionUtils.isEmpty(userDOS);
    }

    /**
     * 检测账号的唯一性
     * @param account
     * @param ops
     */
    public boolean checkAccountUnique(String account, int ops) {
        if (ops == 0) {
            return checkEmailUnique(account);
        } else {
            return checkUserNameUnique(account);
        }
    }

    /**
     * 发送验证码
     * @param email
     */
    public void sendCode(String email) {
        emailAdviceService.sendCode(email);
    }

}