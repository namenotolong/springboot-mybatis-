package com.huyong.controller;

import com.huyong.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: EventController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/EventController")
public class EventController {

    @Resource
    private EventService eventService;
}