package com.huyong.controller;

import com.huyong.annotation.ValidationParam;
import com.huyong.constant.AuthCheckConstant;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.model.UserBO;
import com.huyong.service.UserService;
import com.huyong.utils.AlgorithmUtils;
import com.huyong.utils.AuthUtils;
import com.huyong.utils.ServletUtils;
import io.swagger.annotations.ApiOperation;
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
    public boolean login(@ValidationParam("userName,password") @RequestBody UserBO user) throws Exception {
        final UserDO userDO = userService.getUser(user);
        if (null != userDO) {
            int time = 7 * 24 * 60 * 60;
            final String encrypt = AlgorithmUtils.encrypt(userDO.getMail() + "-" + userDO.getPassword() + "-" + System.currentTimeMillis() + time * 1000);
            ServletUtils.addCookie(AuthCheckConstant.AUTH_TICKET, encrypt, time);
            return true;
        }
        return false;
    }
}
