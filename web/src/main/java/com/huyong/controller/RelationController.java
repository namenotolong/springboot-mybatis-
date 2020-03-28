package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.dao.module.UserBO;
import com.huyong.service.RelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 描述: RelationController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/relation")
@Api("用户关系接口")
public class RelationController {

    @Resource
    private RelationService relationService;

    @ResponseBody
    @GetMapping("/getRelations")
    @ApiOperation("获取用户id与当前用户之间的关系map")
    public Map<String, Boolean> getRelations(@ApiParam("用户id") @RequestParam("id") Long id,
                                             @ApiParam("文章id") @RequestParam("articleId") Long articleId) {
        return relationService.getRelations(id, articleId);
    }

    @ResponseBody
    @GetMapping("/modifyRelation")
    @ApiOperation("更改用户id或者文章id和当前用户的关系")
    @CheckAuth
    public void modifyRelation(@ApiParam("用户id") @RequestParam(value = "userId",required = false) Long userId,
                               @ApiParam("文章id") @RequestParam(value = "articleId", required = false) Long articleId,
                               @ApiParam("topicId") @RequestParam(value = "topicId", required = false) Long topicId,
                               @ApiParam("增加 0 删除 1") @RequestParam("ops") Integer ops,
                               @ApiParam("点赞用户 1 收藏文章 2 关注用户 3 点赞文章 4 点赞评论 5") @RequestParam("type") Integer type) {
        relationService.modifyRelation(userId, articleId, topicId, ops, type);
    }

    @ResponseBody
    @GetMapping("/getRelationCount")
    @ApiOperation("获取用户id或者文章id的关系数目map")
    public Map<String, Long> getRelationCount(@ApiParam("用户id") @RequestParam("userId") Long userId,
                                              @ApiParam("文章id") @RequestParam("articleId") Long articleId) {
        return relationService.getRelationMapCount(userId, articleId);
    }
    @ResponseBody
    @GetMapping("/getFansOrFollows")
    @ApiOperation("获取用户id的所有粉丝、关注")
    public List<UserBO> getFansOrFollows(@ApiParam("用户id") @RequestParam("userId") Long userId,
                                @ApiParam("1：粉丝 0： 关注")@RequestParam("type") Integer type) {
        return relationService.getFansOrFollows(userId, type);
    }
}