package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.annotation.ValidationParam;
import com.huyong.dao.module.UserBO;
import com.huyong.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @CheckAuth
    public UserBO getUser() {
        return userService.getUser();
    }

    @ResponseBody
    @GetMapping("/getOther")
    @ApiOperation("获取其他用户信息")
    public UserBO getOther(@RequestParam("id") @ApiParam("用户id") Long id) {
        return userService.getOther(id);
    }

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public void register(@ApiParam("用户信息") @ValidationParam("email,userName,password,code") @RequestBody UserBO userBO) {
        userService.register(userBO);
    }

    @ResponseBody
    @PostMapping("/reset")
    @ApiOperation("修改密码")
    public void reset(@ApiParam("用户信息") @ValidationParam("email,password,code") @RequestBody UserBO userBO) {
        userService.reset(userBO);
    }

    @ResponseBody
    @GetMapping("/checkAccountUnique")
    @ApiOperation("检测账户唯一性")
    public boolean checkEmail(@ApiParam("账号") @RequestParam("account") String account,
                           @ApiParam("邮箱：0，用户名：1") @RequestParam("ops") int ops) {
        return userService.checkAccountUnique(account, ops);
    }

    @ResponseBody
    @GetMapping("/send")
    @ApiParam("/发送验证码")
    public void sendCode(@ApiParam("邮箱") @RequestParam("email") String email) {
        userService.sendCode(email);
    }

    @ResponseBody
    @PostMapping("/updateOrInsert")
    @ApiOperation("/发送验证码")
    @CheckAuth
    public String updateOrInsert(@ApiParam("用户信息") @ValidationParam("userName,ops") @RequestBody UserBO userBO) {
        return userService.updateOrInsert(userBO);
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation("/获取用户")
    public Map<String, Object> userList(@ApiParam("昵称") @RequestParam(value = "name", required = false) String name,
                                 @ApiParam("性别") @RequestParam(value = "gender", required = false) Integer gender,
                                 @ApiParam("角色集合") @RequestParam(value = "roles", required = false) List<Integer> roles,
                                 @ApiParam("学校") @RequestParam(value = "school", required = false) String school,
                                 @ApiParam("电子邮件") @RequestParam(value = "email", required = false) String email,
                                 @ApiParam("是否在线") @RequestParam(value = "online", required = false) Integer online,
                                 @ApiParam("当前页") @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                 @ApiParam("每页的大小") @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return userService.userList(name, gender, roles, school, email, online, pageNumber, pageSize);
    }

    @ResponseBody
    @ApiOperation("删除用户")
    @PostMapping("/remove")
    public void remove(@RequestBody Map<String, List<Long>> map) {
        userService.remove(map);
    }

    @ResponseBody
    @ApiOperation("获取用户详情")
    @GetMapping("/detail")
    public UserBO detail(@ApiParam("id") @RequestParam("id") Long id) {
        return userService.detail(id);
    }

}