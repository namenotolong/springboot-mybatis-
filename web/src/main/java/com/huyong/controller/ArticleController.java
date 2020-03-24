package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.annotation.ValidationParam;
import com.huyong.dao.module.ArticleBO;
import com.huyong.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @ResponseBody
    @GetMapping("/getArticles")
    @ApiOperation("获取文章")
    public List<ArticleBO> getArticles(@ApiParam("每页显示数量")@RequestParam("pageSize") Long pageSize,
                                       @ApiParam("页码")@RequestParam("pageNum") Long pageNum,
                                       @ApiParam("分类Id") @RequestParam(value = "kindId",required = false) Integer kindId,
                                       @ApiParam("关注者id") @RequestParam(value = "userId", required = false) Long userId,
                                       @ApiParam("文章类型，草稿还是正文") @RequestParam(value = "type", required = false) Integer type) {
        return articleService.getArticles(pageSize, pageNum, kindId, userId, type);
    }
}