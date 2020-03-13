package com.huyong.dao.entity;

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
public class ArticleDO implements Serializable {

    private static final long serialVersionUID = -5961007867814341633L;
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 话题数目
     */
    private Long topicCount;
    /**
     * 点赞数
     */
    private Long praiseCount;
    /**
     * 阅读数
     */
    private Long readCount;
    /**
     * 楼层数
     */
    private Long floorCount;
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


}
