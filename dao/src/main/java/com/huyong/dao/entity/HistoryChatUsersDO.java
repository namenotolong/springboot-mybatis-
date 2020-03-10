package com.huyong.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 历史聊天用户
 *
 * @author huyong
 * @date 2020-03-10 9:46 下午
 */
@Data
public class HistoryChatUsersDO implements Serializable {

    private static final long serialVersionUID = -4477601126207540210L;
    private Long id;
    /**
     * owner
     */
    private Long userId;
    /**
     * 记录用户id
     */
    private Long customer;
    /**
     * 删除 存在
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
