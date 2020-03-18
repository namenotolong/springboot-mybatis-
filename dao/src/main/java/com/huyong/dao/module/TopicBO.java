package com.huyong.dao.module;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * 描述: 评论、回复
 *
 * @author huyong
 * @date 2020-03-10 9:18 下午
 */
@Data
@Table(tableName = "topic")
@ApiModel("评论、回复")
public class TopicBO {

    private Long id;
    @ApiModelProperty("所属文章id")
    private Long articleId;
    @ApiModelProperty("只有回复有，对应的文章评论id")
    private Long topicId;
    @ApiModelProperty("只有回复有，回复的topicId")
    private Long parentId;
    @ApiModelProperty("用户")
    private Long userId;
    @ApiModelProperty("更新用户")
    private Long updateUserId;
    @ApiModelProperty("回复的用户")
    private Long toUserId;
    @ApiModelProperty("点赞数，只有评论有")
    private Long praiseCount;
    @ApiModelProperty("所属的话题总数，只有评论有")
    private Long topicCount;
    @ApiModelProperty("评论/回复")
    private Integer type;
    @ApiModelProperty("删除/正常c")
    private Integer status;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
