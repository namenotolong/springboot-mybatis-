package com.huyong.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述: 用户
 *
 * @author huyong
 * @date 2020-02-21 10:33 下午
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = 70919679543102411L;
    /**
     * 对应数据库id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 密码
     */
    private String password;
    /**
     * 角色id
     */
    private Integer roleCode;
}
