package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 聊天记录
 *
 * @author huyong
 * @date 2020-03-10 11:43 下午
 */
@Data
@Table(tableName = "chat_record")
public class ChatRecordDO implements Serializable {
    private static final long serialVersionUID = -5788065378238550644L;
    private Long id;
    /**
     * 发起者
     */
    private Long fromUserId;
    /**
     * 接受者
     */
    private Long toUserId;
    /**
     * 内容
     */
    private String content;
    /**
     * 删除 存在
     */
    private Integer status;
    /**
     * 已读 未读
     */
    private Integer type;
    /**
     * 消息类型
     */
    private Integer msgType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
