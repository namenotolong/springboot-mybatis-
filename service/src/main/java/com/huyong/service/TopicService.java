package com.huyong.service;

import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.module.TopicBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.TopicMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: TopicService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class TopicService {
    @Resource
    private TopicMapper topicMapper;

    public TopicDO convertBo2Do(TopicBO topic) {
        TopicDO topicDO = new TopicDO();
        BeanUtils.copyProperties(topic, topicDO);
        return topicDO;
    }
    public List<TopicDO> convertBos2Dos(List<TopicBO> topics) {
        if (null == topics || topics.isEmpty()) {
            return new ArrayList<>();
        }
        return topics.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public TopicBO convertDo2Bo(TopicDO topic) {
        TopicBO topicBO = new TopicBO();
        BeanUtils.copyProperties(topic, topicBO);
        return topicBO;
    }
    public List<TopicBO> convertDos2Bos(List<TopicDO> topics) {
        if (null == topics || topics.isEmpty()) {
            return new ArrayList<>();
        }
        return topics.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }
}