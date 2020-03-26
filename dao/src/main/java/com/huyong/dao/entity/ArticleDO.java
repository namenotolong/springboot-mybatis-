package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 文章
 *
 * @author huyong
 * @date 2020-03-10 9:03 下午
 */
@Data
@Table(tableName = "article")
public class ArticleDO implements Serializable {

    private static final long serialVersionUID = -5961007867814341633L;
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章类型 草稿、正文
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;
    /**
     * 分类id集合
     */
    private String kindIds;
    /**
     * 作者
     */
    private Long userId;
    /**
     * 删除/存在
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
    /**
     * 流量量
     */
    private Long visitCount;
}
