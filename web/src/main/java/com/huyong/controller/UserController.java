package com.huyong.controller;

import com.huyong.annotation.NotBlank;
import com.huyong.annotation.ValidationParam;
import com.huyong.dao.module.UserBO;
import com.huyong.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 描述: UserController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("用户登录")
    public UserBO login(@ApiParam("用户信息") @ValidationParam("account,password") @RequestBody UserBO user) {
        return userService.login(user);
    }

    @ResponseBody
    @GetMapping("/getUser")
    @ApiOperation("获取当前用户")
    public UserBO getUser() {
        return userService.getUser();
    }

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public void register(@ApiParam("用户信息") @ValidationParam("email,userName,password") @RequestBody UserBO userBO) {
        userService.register(userBO);
    }

    @ResponseBody
    @GetMapping("/checkAccountUnique")
    @ApiOperation("检测账户唯一性")
    public boolean checkEmail(@ApiParam("账号") @NotBlank @RequestParam("account") String account,
                           @ApiParam("邮箱：0，用户名：1") @NotBlank @RequestParam("ops") int ops) {
        return userService.checkAccountUnique(account, ops);
    }

}