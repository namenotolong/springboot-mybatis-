package com.huyong.service;

import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.model.UserBO;
import com.huyong.utils.AlgorithmUtils;
import com.huyong.utils.AuthUtils;
import com.sun.tools.javac.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述: 验证用户
 *
 * @author huyong
 * @date 2020-02-22 1:29 下午
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public boolean valid(String value) throws Exception {
        final String decrypt = AlgorithmUtils.decrypt(value);
        final String[] split = decrypt.split("-");
        if (split.length != 3) {
            return false;
        }
        final UserBO user = new UserBO();
        final String s = split[2];
        //过期
        if (Long.parseLong(s) < System.currentTimeMillis()) {
            return false;
        }
        user.setMail(split[0]);
        user.setPassword(split[1]);
        final List<UserDO> users = userMapper.queryUsers(user);
        if (CollectionUtils.isEmpty(users)) {
            return false;
        }
        //加入到当前线程中
        AuthUtils.setUser(users.get(0));
        return true;
    }

    public UserDO getUser(UserBO user) {
        final List<UserDO> users = userMapper.queryUsers(user);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }
}
