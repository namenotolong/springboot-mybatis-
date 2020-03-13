package com.huyong.dao.entity;

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
public class UserDO implements Serializable {
    private static final long serialVersionUID = 70919679543102411L;
    private Long id;
    /**
     * 用户   a名
     */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户/管理员
     */
    private Integer role;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 学校
     */
    private String school;
    /**
     * 地址
     */
    private String address;
    /**
     * 工作岗位
     */
    private String work;
    /**
     * 个人简介
     */
    private String introduction;
    /**
     * 上线/离线
     */
    private Integer online;
    /**
     * 头像
     */
    private String picture;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 存在/删除
     */
    private Integer status;
    /**
     * 关注的文章集合id
     */
    private String articles;
    /**
     * 浏览量
     */
    private Long visitCount;
}
