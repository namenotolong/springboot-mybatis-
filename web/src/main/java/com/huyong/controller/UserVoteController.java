package com.huyong.controller;

import com.huyong.service.UserVoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: UserVoteController
 *
 * @author yangtao
 * @date 2020-05-17 21:07
 */
@Controller
@RequestMapping("/UserVoteController")
public class UserVoteController {

    @Resource
    private UserVoteService userVoteService;
}
