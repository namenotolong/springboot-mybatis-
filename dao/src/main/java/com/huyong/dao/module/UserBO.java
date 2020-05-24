package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * 描述: 用户
 *
 * @author huyong
 * @date 2020-02-21 10:33 下午
 */
@Data
@Table(tableName = "user")
@ApiModel("用户")
public class UserBO {
    private Long id;
    @ApiModelProperty("用户名")
    private String name;
}
