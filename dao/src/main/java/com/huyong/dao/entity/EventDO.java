package com.huyong.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 事件
 *
 * @author huyong
 * @date 2020-03-10 9:31 下午
 */
@Data
public class EventDO implements Serializable {
    private static final long serialVersionUID = -8077869910633421281L;
    private Long id;
    /**
     * 发起者
     */
    private Long fromUserId;
    /**
     * 收到者
     */
    private Long toUserId;
    /**
     * 回复评论对应的id
     */
    private Long topicId;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 已读/未读
     */
    private Integer type;
    /**
     * 删除/正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
