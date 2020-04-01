package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
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
@Table(tableName = "event")
public class EventDO implements Serializable {
    private static final long serialVersionUID = -8077869910633421281L;
    private Long id;
    /**
     * 这条消息归属的用户，作为聊天记录的时候，一条消息应该有存两条记录，分别两个owner
     */
    private Long owner;
    /**
     * 内容
     */
    private String content;
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
     * 回复评论对应的根评论id
     */
    private Long rootId;
    /**
     * 文章id，回复为空
     */
    private Long articleId;
    /**
     * 事件类型
     */
    private Integer type;
    /**
     * 0 未读 1 已读
     */
    private Integer status;
    /**
     * 1 删除 0 存在
     */
    private Integer present;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
