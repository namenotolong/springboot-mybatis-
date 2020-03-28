package com.huyong.controller;

import com.huyong.annotation.CheckAuth;
import com.huyong.annotation.ValidationParam;
import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.module.TopicBO;
import com.huyong.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: TopicController
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Controller
@RequestMapping("/topic")
@Api("topic相关接口")
public class TopicController {

    @Resource
    private TopicService topicService;

    @ResponseBody
    @GetMapping("/getCommons")
    @ApiOperation("获取文章评论以及回复")
    public List<TopicBO> getCommons(@ApiParam("文章id")@RequestParam("articleId") Long articleId,
                                    @ApiParam("每页显示数量")@RequestParam("pageSize") Integer pageSize,
                                    @ApiParam("页码")@RequestParam("pageNum") Integer pageNum) {
        return topicService.getCommons(articleId, pageSize, pageNum);
    }

    @ResponseBody
    @GetMapping("/getUserTopics")
    @ApiOperation("获取用户的评论以及回复")
    public List<TopicBO> getUserTopics(@ApiParam("用户id")@RequestParam("userId") Long userId,
                                    @ApiParam("每页显示数量")@RequestParam("pageSize") Integer pageSize,
                                    @ApiParam("页码")@RequestParam("pageNum") Integer pageNum) {
        return topicService.getUserTopics(userId, pageSize, pageNum);
    }

    @ResponseBody
    @PostMapping("/publishTopic")
    @ApiOperation("/发表评论、回复")
    @CheckAuth
    public TopicBO publishTopic(@ApiParam("topic") @ValidationParam("type,articleId,content,toUserId") @RequestBody TopicBO topicBO) {
        return topicService.publishTopic(topicBO);
    }

    @ResponseBody
    @GetMapping("/delete")
    @ApiOperation("删除一个评论或者回复")
    @CheckAuth
    public void delete(@ApiParam("topicId") @RequestParam("topicId") Long topicId) {
        topicService.delete(topicId);
    }

    @ResponseBody
    @PostMapping("/modifyTopic")
    @ApiOperation("/修改评论、回复")
    @CheckAuth(1)
    public void modifyTopic(@ApiParam("topic") @ValidationParam("id") @RequestBody TopicBO topicBO) {
        topicService.modifyTopic(topicBO);
    }
}