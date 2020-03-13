package com.huyong.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * 描述: 用户信息
 *
 * @author huyong
 * @date 2020-02-22 1:49 下午
 */
@Data
@ApiModel("用户信息")
public class UserBO {
    @ApiModelProperty("对应数据库id")
    private Long id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("角色名称集合")
    private Integer role;
    @ApiModelProperty("性别")
    private Integer gender;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("学校")
    private String school;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("工作岗位")
    private String work;
    @ApiModelProperty("个人简介")
    private String introduction;
    @ApiModelProperty("上线/离线")
    private Integer online;
    @ApiModelProperty("头像")
    private String picture;
    @ApiModelProperty("注册时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("存在/删除")
    private Integer status;
    @ApiModelProperty("关注的文章集合id")
    private String articles;
    @ApiModelProperty("浏览量")
    private Long visitCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBO)) return false;
        UserBO userBO = (UserBO) o;
        return getEmail().equals(userBO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
