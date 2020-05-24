package com.huyong.controller;

import com.huyong.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: UserController
 *
 * @author yangtao
 * @date 2020-05-17 21:07
 */
@Controller
@RequestMapping("/UserController")
public class UserController {

    @Resource
    private UserService userService;
}
