package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述: 文件
 *
 * @author huyong
 * @date 2020-03-12 2:35 下午
 */
@Data
@Table(tableName = "file")
public class FileDO implements Serializable {
    private static final long serialVersionUID = -8817375713686827039L;
    private Long id;
    /**
     * 文件地址
     */
    private String url;
    /**
     * 文件类型
     */
    private Integer type;
    /**
     * 引用文件的实体类型
     */
    private Integer ref;
    /**
     * 引用对用数据库的id
     */
    private Long refId;
}
