package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
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
@Table(tableName = "bar")
public class BarDO implements Serializable {
    private static final long serialVersionUID = -5229858910191478179L;
    private Long id;
    private String content;
}
