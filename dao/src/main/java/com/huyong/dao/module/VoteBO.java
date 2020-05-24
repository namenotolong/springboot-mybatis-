package com.huyong.dao.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 * 描述: vote
 *
 * @author huyong
 * @date 2020-05-17 9:17 下午
 */
@Data
@ApiModel("vote")
public class VoteBO {
    private Long id;
    private Long voteNumber;
    private Long tickets;
    private String name;
    private Boolean isVote;
}
