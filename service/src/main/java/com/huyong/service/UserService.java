package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.UserMapper;

/**
 * 描述: UserService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
}