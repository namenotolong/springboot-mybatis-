package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述: 分类
 *
 * @author huyong
 * @date 2020-03-10 9:04 下午
 */
@Data
@Table(tableName = "kind")
@ApiModel("分类")
public class KindBO {
    private Long id;
    @ApiModelProperty("父级菜单")
    private Long parentId;
    @ApiModelProperty("分类名称")
    private String kindName;
    @ApiModelProperty("创建者")
    private Long userId;
    @ApiModelProperty("修改者")
    private Long updateUserId;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("删除/存在")
    private Integer status;
    @ApiModelProperty("二级目录")
    private List<KindBO> children;
}
