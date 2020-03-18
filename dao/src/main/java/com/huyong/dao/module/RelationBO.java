package com.huyong.dao.module;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;


/**
 * 描述: 用户之间的关系
 *
 * @author huyong
 * @date 2020-03-10 9:40 下午
 */
@Data
@Table(tableName = "relation")
@ApiModel("用户之间的关系")
public class RelationBO {

    private Long id;
    @ApiModelProperty("一")
    private Long userId;
    @ApiModelProperty("多")
    private String othersId;
    @ApiModelProperty("关系类型")
    private Integer type;
    @ApiModelProperty("数量")
    private Long count;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
