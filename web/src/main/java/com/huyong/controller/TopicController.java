package com.huyong.controller;

import com.huyong.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: TopicController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/TopicController")
public class TopicController {

    @Resource
    private TopicService topicService;
}