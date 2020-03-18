package com.huyong.controller;

import com.huyong.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * 描述: ArticleController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/ArticleController")
public class ArticleController {

    @Resource
    private ArticleService articleService;
}