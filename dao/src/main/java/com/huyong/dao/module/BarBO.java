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
@ApiModel("bar")
public class BarBO {
    private Long id;
    private String content;
}
