package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.UserMapper;

/**
 * 描述: UserService
 *
 * @author huyong
 * @date 2020-05-17 21:07
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
}