package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;


/**
 * 描述: 文件
 *
 * @author huyong
 * @date 2020-03-12 2:35 下午
 */
@Data
@Table(tableName = "file")
@ApiModel("文件")
public class FileBO {
    private Long id;
    @ApiModelProperty("文件地址")
    private String url;
    @ApiModelProperty("文件类型")
    private Integer type;
    @ApiModelProperty("引用文件的实体类型")
    private Integer ref;
    @ApiModelProperty("引用对用数据库的id")
    private Long refId;
    @ApiModelProperty("创建事件")
    private Date createTime;
}
