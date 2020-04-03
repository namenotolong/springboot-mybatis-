package com.huyong.service;

import com.huyong.constant.AuthCheckConstant;
import com.huyong.constant.CommonConstant;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.module.UserBO;
import com.huyong.enums.*;
import com.huyong.exception.AuthException;
import com.huyong.exception.CommonException;
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
    @Resource
    private RelationService relationService;

    public UserDO convertBo2Do(UserBO user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getDesc().equals(user.getSex())) {
                userDO.setGender(value.getCode());
            }
        }
        return userDO;
    }

    /**
     * 返回更新指定字段的userDO
     * @param user
     * @return
     */
    public UserDO convertBo2UpdateDo(UserBO user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.getId());
        userDO.setUserName(user.getUserName());
        userDO.setAddress(user.getAddress());
        userDO.setAge(user.getAge());
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getDesc().equals(user.getSex())) {
                userDO.setGender(value.getCode());
            }
        }
        userDO.setIntroduction(user.getIntroduction());
        userDO.setSchool(user.getSchool());
        userDO.setWork(user.getWork());
        return userDO;
    }

    /**
     * 可供可见的属性userBO
     * @param user
     * @return
     */
    public UserBO convertDo2BoOnlySee(UserDO user) {
        UserBO result = new UserBO();
        result.setId(user.getId());
        result.setUserName(user.getUserName());
        result.setAddress(user.getAddress());
        result.setAge(user.getAge());
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getCode().equals(user.getGender())) {
                result.setSex(value.getDesc());
            }
        }
        result.setIntroduction(user.getIntroduction());
        result.setSchool(user.getSchool());
        result.setWork(user.getWork());
        result.setPicture(user.getPicture());
        return result;
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
        if (userBO.getGender() != null) {
            GenderEnum[] values = GenderEnum.values();
            for (GenderEnum value : values) {
                if (value.getCode().equals(userBO.getGender())) {
                    userBO.setSex(value.getDesc());
                }
            }
        }
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
        userDO.setOnline(OnlineEnum.OFFLINE.getCode());
        userDO.setPicture("/img/default.jpg");
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
    private boolean checkUserNameUnique(String userName, Long id) {
        UserDO userDO = modifyUserDO();
        //验证用户名唯一性
        userDO.setUserName(userName);
        List<UserDO> userDOS = userMapper.queryByCondition(userDO);
        return CollectionUtils.isEmpty(userDOS) || (userDOS.size() == 1 && userDOS.get(0).getId().equals(id));
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

    /**
     * 新增或者更新一个用户
     * @param userBO
     */
    public String updateOrInsert(UserBO userBO) {
        if (userBO.getOps().equals(OpsEnum.UPDATE.getCode())) {
            if (userBO.getId() == null) {
                throw new CommonException("id参数为空");
            }
            boolean unique = checkUserNameUnique(userBO.getUserName(), userBO.getId());
            if (!unique) {
                throw new CommonException("更改昵称已被他人使用");
            }
            //如果是管理员 直接更新，否则只能更新指定字段
            UserDO userDO;
            if (AuthUtils.getUser().getRole().equals(RoleEnum.ADMIN.getCode())) {
                userDO = convertBo2Do(userBO);
            } else {
                if (!AuthUtils.getUser().getId().equals(userBO.getId())){
                    throw new CommonException("您不能修改别人的资料！");
                }
                userDO = convertBo2UpdateDo(userBO);
            }
            userMapper.updateByPrimary(userDO);
            return AuthCheckConstant.START + JwtHelper.createJWT(userDO.getId(), userDO.getUserName(), AuthUtils.getUser().getEmail(), AuthUtils.getUser().getRole());
        } else {
            //只有管理员能够插入
            if (!AuthUtils.getUser().getRole().equals(RoleEnum.ADMIN.getCode())) {
                throw new CommonException("权限不足！");
            }
            boolean unique = checkUserNameUnique(userBO.getUserName(), userBO.getId());
            if (!unique) {
                throw new CommonException("更改昵称已被他人使用");
            }
            userMapper.insert(convertBo2Do(userBO));
            return null;
        }
    }

    /**
     * 个人主页中获取用户信息
     * @param id
     * @return
     */
    public UserBO getOther(Long id) {
        UserDO userDO = userMapper.selectByPrimary(id);
        if (null != userDO) {
            final UserBO userBO = convertDo2BoOnlySee(userDO);
            //如果当前存在登录用户，则附带关注情况
            if (AuthUtils.getUser() != null) {
                boolean present = relationService.checkRelation(AuthUtils.getUser().getId(), userBO.getId(), RelationEnum.FOLLOW.getCode());
                userBO.setFollow(present);
            }
            return userBO;
        }
        return new UserBO();
    }

    /**
     * 重置密码
     * @param userBO
     */
    public void reset(UserBO userBO) {
        emailAdviceService.checkCode(userBO.getEmail(), userBO.getCode());
        UserDO condition = new UserDO();
        UserDO target = new UserDO();
        condition.setEmail(userBO.getEmail());
        target.setPassword(userBO.getPassword());
        userMapper.updateByCondition(target, condition);
    }
}