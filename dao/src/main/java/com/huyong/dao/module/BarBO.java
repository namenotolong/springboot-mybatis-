package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述: 头部功能
 *
 * @author huyong
 * @date 2020-03-10 9:14 下午
 */
@Data
@Table(tableName = "bar")
@ApiModel("头部功能")
public class BarBO {
    private Long id;
    @ApiModelProperty("父级")
    private Long parentId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("地址")
    private String url;
    @ApiModelProperty("图片地址")
    private String icon;
    @ApiModelProperty("创建用户")
    private Long userId;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("删除/存在")
    private Integer status;
    @ApiModelProperty("左边导航/右边导航")
    private Integer type;
    @ApiModelProperty("二级目录")
    private List<BarBO> children;
}
