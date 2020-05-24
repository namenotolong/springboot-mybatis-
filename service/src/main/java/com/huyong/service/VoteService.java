package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.VoteMapper;

/**
 * 描述: VoteService
 *
 * @author huyong
 * @date 2020-05-17 21:07
 */
@Service
public class VoteService {
    @Resource
    private VoteMapper voteMapper;
}