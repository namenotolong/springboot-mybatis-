package com.huyong.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 评论、回复
 *
 * @author huyong
 * @date 2020-03-10 9:18 下午
 */
@Data
public class TopicDO implements Serializable {

    private static final long serialVersionUID = 1333988147109081465L;
    private Long id;
    /**
     * 所属文章id
     */
    private Long articleId;
    /**
     * 只有回复有，对应的文章评论id
     */
    private Long topicId;
    /**
     * 只有回复有，回复的topicId
     */
    private Long parentId;
    /**
     * 用户
     */
    private Long userId;
    /**
     * 更新用户
     */
    private Long updateUserId;
    /**
     * 回复的用户
     */
    private Long toUserId;
    /**
     * 点赞数，只有评论有
     */
    private Long praiseCount;
    /**
     * 所属的话题总数，只有评论有
     */
    private Long topicCount;
    /**
     * 评论/回复
     */
    private Integer type;
    /**
     * 删除/正常c
     */
    private Integer status;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
