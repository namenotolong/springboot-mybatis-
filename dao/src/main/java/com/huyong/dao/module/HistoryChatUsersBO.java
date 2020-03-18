package com.huyong.dao.module;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * 描述: 历史聊天用户
 *
 * @author huyong
 * @date 2020-03-10 9:46 下午
 */
@Data
@Table(tableName = "history_chat_user")
@ApiModel("历史聊天用户")
public class HistoryChatUsersBO {

    private Long id;
    @ApiModelProperty("owner")
    private Long userId;
    @ApiModelProperty("记录用户id")
    private Long customer;
    @ApiModelProperty("删除 存在")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
