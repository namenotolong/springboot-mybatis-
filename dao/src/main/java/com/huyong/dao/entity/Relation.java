package com.huyong.dao.entity;

import java.util.Date;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述: 用户之间的关系
 *
 * @author huyong
 * @date 2020-03-10 9:40 下午
 */
@Data
public class Relation implements Serializable {

    private static final long serialVersionUID = -7820153158260303420L;
    private Long id;
    /**
     * 一
     */
    private Long userId;
    /**
     * 多
     */
    private String othersId;
    /**
     * 关系类型
     */
    private Integer type;
    /**
     * 数量
     */
    private Long count;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
