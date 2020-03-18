package com.huyong.controller;

import com.huyong.service.HistoryChatUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: HistoryChatUsersController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/HistoryChatUsersController")
public class HistoryChatUsersController {

    @Resource
    private HistoryChatUsersService historyChatUsersService;
}