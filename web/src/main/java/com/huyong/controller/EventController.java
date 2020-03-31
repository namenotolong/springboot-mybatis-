package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.common.PageTemp;
import com.huyong.dao.entity.EventDO;
import com.huyong.dao.module.EventBO;
import com.huyong.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 描述: EventController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/event")
@Api("事件接口")
public class EventController {

    @Resource
    private EventService eventService;

    @ResponseBody
    @GetMapping("/unReadCount")
    @ApiOperation("获取用户所有未读消息数目")
    @CheckAuth
    public Integer unReadCount() {
        return eventService.unReadCount();
    }

    @ResponseBody
    @GetMapping("/unReadCountMap")
    @ApiOperation("获取用户所有未读消息数目")
    @CheckAuth
    public Map<String, Long> unReadCountMap() {
        return eventService.unReadCountMap();
    }

    @ResponseBody
    @GetMapping("/getEvents")
    @ApiOperation("获取具体事件")
    @CheckAuth
    public PageTemp<EventBO> getEvents(@ApiParam("type事件类型") @RequestParam("type") Integer type,
                                       @ApiParam("每页显示数量")@RequestParam("pageSize") Integer pageSize,
                                       @ApiParam("页码")@RequestParam("pageNum") Integer pageNum) {
        return eventService.getEvents(type, pageSize, pageNum);
    }

    @ResponseBody
    @PostMapping("/setZero")
    @ApiOperation("清零未读")
    @CheckAuth
    public void setZero(@ApiParam("type事件类型") @RequestParam("type") Integer type) {
        eventService.setZero(type);
    }

    @ResponseBody
    @GetMapping("/getChatList")
    @ApiOperation("获取用户的聊天列表")
    @CheckAuth
    public List<EventDO> getChatList() {
        return eventService.getChatList();
    }

}