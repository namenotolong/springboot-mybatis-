package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

/**
 * 描述: userVote
 *
 * @author huyong
 * @date 2020-05-17 9:18 下午
 */
@Data
@Table(tableName = "user_vote")
public class UserVoteDO {
    private Long id;
    private Long userId;
    private Long voteId;
}
