package com.huyong.controller;

import com.huyong.annotation.ValidationParam;
import com.huyong.dao.model.UserBO;
import com.huyong.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login() throws Exception {
        return userService.login();
    }
}
