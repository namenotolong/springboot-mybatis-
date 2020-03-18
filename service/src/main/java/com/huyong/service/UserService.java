package com.huyong.service;

import com.google.common.collect.Maps;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.model.UserBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    public String login() {
        final UserDO userDO = userMapper.selectByPrimary(1L);
        System.out.println(userDO);
        return null;
    }
}
