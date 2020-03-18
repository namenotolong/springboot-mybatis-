package com.huyong.controller;

import com.huyong.service.KindService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: KindController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/KindController")
public class KindController {

    @Resource
    private KindService kindService;
}