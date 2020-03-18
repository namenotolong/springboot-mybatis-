package com.huyong.controller;

import com.huyong.annotation.ValidationParam;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.model.UserBO;
import com.huyong.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 描述: 用户控制器
 *
 * @author huyong
 * @date 2020-02-22 1:43 下午
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private Interf t;


    @ResponseBody
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(@ValidationParam("email,password") @RequestBody UserBO user) throws Exception {
        return userService.login(user);
    }

    @GetMapping("/get")
    @ResponseBody
    @ApiOperation("获取用户")
    public UserDO getUsers() {
        return userMapper.get();
    }
    @GetMapping("/test")
    @ResponseBody
    @ApiOperation("test")
    public UserDO test(@RequestParam("id") Integer id) {
        return userMapper.test(id);
    }
    @GetMapping("/count")
    @ResponseBody
    @ApiOperation("count")
    public int count() {
        userService.count();
        return 1;
    }
}
