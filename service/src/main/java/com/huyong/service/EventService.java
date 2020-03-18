package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.EventMapper;

/**
 * 描述: EventService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class EventService {
    @Resource
    private EventMapper eventMapper;
}