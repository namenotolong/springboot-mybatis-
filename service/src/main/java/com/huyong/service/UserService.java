package com.huyong.service;

import com.google.common.collect.Maps;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.model.UserBO;
import com.huyong.dao.util.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    /**
     * 登录用户缓存
     * key : 用户信息
     * value : 有效时间
     */
    static Map<UserBO, Long> users = Maps.newConcurrentMap();


    public String login(UserBO user) {
        final Long delayTime = users.get(user);
        //缓存中没有，读取数据库
        if (null == delayTime) {

        } else {

        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void count() {
        final UserDO userDO1 = new UserDO();
        final UserDO userDO12= new UserDO();
        userDO1.setUserName("1223");
        userDO1.setPassword("122123");
        userDO12.setId(2L);
        userDO12.setPassword("123123");
        userMapper.updateByCondition(userDO1, userDO12);
        userMapper.deleteByCondition(userDO12);
    }
}
