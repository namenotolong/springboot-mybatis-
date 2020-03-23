package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * 描述: 文章
 *
 * @author huyong
 * @date 2020-03-10 9:03 下午
 */
@Data
@Table(tableName = "article")
@ApiModel("文章")
public class ArticleBO {

    private Long id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("文章类型")
    private Integer type;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("话题数目")
    private Long topicCount;
    @ApiModelProperty("点赞数")
    private Long praiseCount;
    @ApiModelProperty("阅读数")
    private Long readCount;
    @ApiModelProperty("楼层数")
    private Long floorCount;
    @ApiModelProperty("分类id集合")
    private String kindIds;
    @ApiModelProperty("作者")
    private Long userId;
    @ApiModelProperty("删除/存在")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("操作类型")
    private Integer ops;
}
