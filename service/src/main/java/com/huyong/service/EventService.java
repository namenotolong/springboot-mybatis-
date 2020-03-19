package com.huyong.service;

import com.huyong.dao.entity.EventDO;
import com.huyong.dao.module.EventBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.EventMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: EventService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class EventService {
    @Resource
    private EventMapper eventMapper;

    public EventDO convertBo2Do(EventBO event) {
        EventDO eventDO = new EventDO();
        BeanUtils.copyProperties(event, eventDO);
        return eventDO;
    }
    public List<EventDO> convertBos2Dos(List<EventBO> events) {
        if (null == events || events.isEmpty()) {
            return new ArrayList<>();
        }
        return events.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public EventBO convertDo2Bo(EventDO event) {
        EventBO eventBO = new EventBO();
        BeanUtils.copyProperties(event, eventBO);
        return eventBO;
    }
    public List<EventBO> convertDos2Bos(List<EventDO> events) {
        if (null == events || events.isEmpty()) {
            return new ArrayList<>();
        }
        return events.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }
}