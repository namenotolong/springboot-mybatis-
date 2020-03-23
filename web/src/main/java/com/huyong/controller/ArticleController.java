package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.annotation.ValidationParam;
import com.huyong.dao.module.ArticleBO;
import com.huyong.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 描述: ArticleController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/article")
@Api("文章相关接口")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ResponseBody
    @PostMapping("/publishOrModify")
    @CheckAuth
    @ApiOperation("发布或者修改文章")
    public void publishOrModify(@ApiParam("文章") @RequestBody @ValidationParam("title,ops,type") ArticleBO articleBO) {
        articleService.publishOrModify(articleBO);
    }
}