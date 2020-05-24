package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述: userVote
 *
 * @author huyong
 * @date 2020-05-17 9:18 下午
 */
@ApiModel("userVote")
@Data
public class UserVoteBO {
    private Long id;
    private Long userId;
    private Long voteId;
}
