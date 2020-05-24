package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 用户
 *
 * @author huyong
 * @date 2020-02-21 10:33 下午
 */
@Data
@Table(tableName = "user")
public class UserDO implements Serializable {
    private static final long serialVersionUID = 70919679543102411L;
    private Long id;
    /**
     * 用户名
     */
    private String name;
}
