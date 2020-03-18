package com.huyong.controller;

import com.huyong.service.BarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: BarController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/BarController")
public class BarController {

    @Resource
    private BarService barService;
}