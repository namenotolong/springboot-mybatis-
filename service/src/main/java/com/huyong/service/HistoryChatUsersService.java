package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.HistoryChatUsersMapper;

/**
 * 描述: HistoryChatUsersService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class HistoryChatUsersService {
    @Resource
    private HistoryChatUsersMapper historyChatUsersMapper;
}