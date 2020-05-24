package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.UserVoteMapper;

/**
 * 描述: UserVoteService
 *
 * @author huyong
 * @date 2020-05-17 21:07
 */
@Service
public class UserVoteService {
    @Resource
    private UserVoteMapper userVoteMapper;
}