package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 邮箱通知
 *
 * @author huyong
 * @date 2020-03-20 1:50 下午
 */
@Data
@Table(tableName = "email_advice")
public class EmailAdviceDO implements Serializable {
    private static final long serialVersionUID = -6626326098025386427L;
    private Long id;
    /**
     * 发送者
     */
    private String from;
    /**
     * 接受者
     */
    private String owner;
    /**
     * 信息
     */
    private String data;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 有效时间戳
     */
    private Long expireTime;
    /**
     * 通知类型
     */
    private Integer type;
}
