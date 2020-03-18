package com.huyong.controller;

import com.huyong.service.RelationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: RelationController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/RelationController")
public class RelationController {

    @Resource
    private RelationService relationService;
}