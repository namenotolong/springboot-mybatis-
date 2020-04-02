package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.dao.module.HistoryChatUsersBO;
import com.huyong.service.HistoryChatUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: HistoryChatUsersController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/history")
@Api("历史聊天用户接口")
public class HistoryChatUsersController {

    @Resource
    private HistoryChatUsersService historyChatUsersService;

    @ResponseBody
    @GetMapping("/getUsers")
    @ApiOperation("获取历史聊天的用户")
    @CheckAuth
    public List<HistoryChatUsersBO> getUsers() {
        return historyChatUsersService.getUsers();
    }

    @ResponseBody
    @GetMapping("/add")
    @ApiOperation("增加最近聊天人")
    @CheckAuth
    public void add(@ApiParam("用户id") @RequestParam("id") Long id) {
        historyChatUsersService.add(id);
    }
    @ResponseBody
    @GetMapping("/remove")
    @ApiOperation("移除最近聊天人")
    @CheckAuth
    public void remove(@ApiParam("id") @RequestParam("id") Long id) {
        historyChatUsersService.remove(id);
    }
}