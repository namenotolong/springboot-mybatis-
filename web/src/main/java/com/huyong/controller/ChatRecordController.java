package com.huyong.controller;

import com.huyong.service.ChatRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: ChatRecordController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/ChatRecordController")
public class ChatRecordController {

    @Resource
    private ChatRecordService chatRecordService;
}