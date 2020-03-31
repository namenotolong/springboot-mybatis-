package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * 描述: 事件
 *
 * @author huyong
 * @date 2020-03-10 9:31 下午
 */
@Data
@Table(tableName = "event")
@ApiModel("事件")
public class EventBO {
    private Long id;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("发起者")
    private Long fromUserId;
    @ApiModelProperty("发起者")
    private String userName;
    @ApiModelProperty("发起者头像")
    private String picture;
    @ApiModelProperty("收到者")
    private Long toUserId;
    @ApiModelProperty("回复评论对应的id,toUser")
    private Long topicId;
    @ApiModelProperty("回复评论对应根评论id")
    private Long rootId;
    @ApiModelProperty("回复的评论内容,toUser")
    private String replyContent;
    @ApiModelProperty("文章id")
    private Long articleId;
    @ApiModelProperty("回复的文章标题")
    private String title;
    @ApiModelProperty("已读/未读")
    private Integer type;
    @ApiModelProperty("删除/正常")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
