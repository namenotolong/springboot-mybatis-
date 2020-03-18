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
    @ApiModelProperty("发起者")
    private Long fromUserId;
    @ApiModelProperty("收到者")
    private Long toUserId;
    @ApiModelProperty("回复评论对应的id")
    private Long topicId;
    @ApiModelProperty("文章id")
    private Long articleId;
    @ApiModelProperty("已读/未读")
    private Integer type;
    @ApiModelProperty("删除/正常")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
