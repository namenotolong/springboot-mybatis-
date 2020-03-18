package com.huyong.dao.module;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * 描述: 聊天记录
 *
 * @author huyong
 * @date 2020-03-10 11:43 下午
 */
@Data
@Table(tableName = "chat_record")
@ApiModel("聊天记录")
public class ChatRecordBO {
    private Long id;
    @ApiModelProperty("发起者")
    private Long fromUserId;
    @ApiModelProperty("接受者")
    private Long toUserId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("删除 存在")
    private Integer status;
    @ApiModelProperty("已读 未读")
    private Integer type;
    @ApiModelProperty("消息类型")
    private Integer msgType;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;
}
