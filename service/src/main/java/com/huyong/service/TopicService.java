package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.TopicMapper;

/**
 * 描述: TopicService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class TopicService {
    @Resource
    private TopicMapper topicMapper;
}