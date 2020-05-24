package com.huyong.dao.entity;

import com.huyong.dao.helper.annotation.Table;
import lombok.Data;

/**
 * 描述: vote
 *
 * @author huyong
 * @date 2020-05-17 9:17 下午
 */
@Data
@Table(tableName = "vote")
public class VoteDO {
    private Long id;
    private Long voteNumber;
    private Long tickets;
    private String name;
}
