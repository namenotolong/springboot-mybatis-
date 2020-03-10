package com.huyong.dao.model;

import com.sun.tools.javac.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private String mail;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("角色名称集合")
    private List<String> roleNames;
}
