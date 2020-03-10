package com.huyong.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 头部功能
 *
 * @author huyong
 * @date 2020-03-10 9:14 下午
 */
@Data
public class BarDO implements Serializable {
    private static final long serialVersionUID = -5229858910191478179L;
    private Long id;
    /**
     * 父级
     */
    private Long parentId;
    /**
     * 内容
     */
    private String content;
    /**
     * 地址
     */
    private String url;
    /**
     * 图片地址
     */
    private String pictureUrl;
    /**
     * 创建用户
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除/存在
     */
    private Integer status;
}
