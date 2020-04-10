package com.huyong.service;

import com.google.common.collect.Maps;
import com.huyong.common.PageTemp;
import com.huyong.dao.entity.EventDO;
import com.huyong.dao.helper.Sort;
import com.huyong.dao.module.EventBO;
import com.huyong.dao.module.TopicBO;
import com.huyong.enums.EventTypeEnum;
import com.huyong.enums.StatusEnum;
import com.huyong.enums.TypeEnum;
import com.huyong.thread.MessageSchedule;
import com.huyong.thread.MessageThreadPool;
import com.huyong.thread.NamedThreadFactory;
import com.huyong.utils.AuthUtils;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.EventMapper;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 描述: EventService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);
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

    public EventDO wrapDO() {
        EventDO eventDO = new EventDO();
        eventDO.setStatus(0);
        return eventDO;
    }

    public void advice(Long fromUserId, Long toUserId, Long articleId, Long topicId, String content, Integer type, Long rootId) {
        if (fromUserId.equals(toUserId)) {
            return;
        }
        log.info("[{}]发送者，接受者[{}],文章[{}],内容[{}],评论[{}]", fromUserId, toUserId, articleId, content, topicId);
        EventDO eventDO = wrapDO();
        eventDO.setFromUserId(fromUserId);
        eventDO.setTopicId(topicId);
        eventDO.setContent(content);
        eventDO.setToUserId(toUserId);
        eventDO.setArticleId(articleId);
        eventDO.setType(type);
        eventDO.setRootId(rootId);
        eventMapper.insert(eventDO);
    }

    /**
     * 关注用户、点赞用户文章通知事件
     * @param fromUserId
     * @param toUserId
     * @param type
     * @param articleId
     */
    public void advice(Long fromUserId, Long toUserId, Integer type, Long articleId, Long topicId) {
        if (fromUserId.equals(toUserId)) {
            return;
        }
        advice(fromUserId, toUserId, articleId, topicId, null, type, null);
    }

    /**
     * 获取未读消息数量
     * @return
     */
    public Integer unReadCount() {
        EventDO condition = new EventDO();
        condition.setToUserId(AuthUtils.getUser().getId());
        condition.setStatus(0);
        return eventMapper.count(condition);
    }

    /**
     * 获取各项未读的map
     * @return
     */
    public Map<String, Long> unReadCountMap() {
        EventDO condition = new EventDO();
        condition.setToUserId(AuthUtils.getUser().getId());
        condition.setStatus(0);
        final List<EventDO> events = eventMapper.queryByCondition(condition);
        Long follow = events.stream().filter(x -> x.getType().equals(EventTypeEnum.FOLLOW.getCode())).count();
        Long praise = events.stream().filter(x -> x.getType().equals(EventTypeEnum.PRAISE.getCode())).count();
        Long system = events.stream().filter(x -> x.getType().equals(EventTypeEnum.SYSTEM.getCode())).count();
        Long reply = events.stream().filter(x -> x.getType().equals(EventTypeEnum.REPLY.getCode())).count();
        Long message = events.stream().filter(x -> x.getType().equals(EventTypeEnum.MESSAGE.getCode())).count();
        Map<String, Long> map = new HashMap<>(5);
        map.put("follow", follow);
        map.put("praise", praise);
        map.put("system", system);
        map.put("reply", reply);
        map.put("message", message);
        return map;
    }

    /**
     * 获取点赞、回复、关注、系统消息的事件分页列表
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageTemp<EventBO> getEvents(Integer type, Integer pageSize, Integer pageNum) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        int offset = (pageNum - 1) * pageSize;
        if (type.equals(EventTypeEnum.SYSTEM.getCode())) {
            EventDO condition = new EventDO();
            condition.setType(EventTypeEnum.SYSTEM.getCode());
            List<EventDO> list = eventMapper.queryByPageWithSort(condition, offset, pageSize, new Sort("create_time", Sort.SortType.DESC));
            int count = eventMapper.count(condition);
            return new PageTemp<>(convertDos2Bos(list), pageSize, pageNum, count);
        }
        List<EventBO> list = eventMapper.getEventBosByTypeWithPageOrderByTime(offset, pageSize, type, AuthUtils.getUser().getId());
        if (CollectionUtils.isNotEmpty(list)) {
            int count = eventMapper.getEventBosByTypeCount(type, AuthUtils.getUser().getId());
            return new PageTemp<>(list, pageSize, pageNum, count);
        }
        return new PageTemp<>();
    }

    /**
     * 清零未读
     * @param type
     */
    public void setZero(Integer type) {
        EventDO condition = new EventDO();
        EventDO target = new EventDO();
        condition.setType(type);
        target.setStatus(1);
        eventMapper.updateByCondition(target, condition);
    }

    /**
     * 获取当前用户的聊天列表
     * @return
     */
    public List<EventBO> getChatList() {
        return eventMapper.getChats(AuthUtils.getUser().getId());
    }

    /**
     * 获取与当前用户的聊天记录
     * @param id
     * @return
     */
    public List<EventBO> getRecord(Long id) {
        return eventMapper.getRecord(id, AuthUtils.getUser().getId());
    }

    /**
     * 消除聊天未读数
     * @param id
     */
    public void setChatZero(Long id) {
        EventDO condition = new EventDO();
        EventDO target = new EventDO();
        condition.setType(EventTypeEnum.MESSAGE.getCode());
        target.setStatus(1);
        condition.setFromUserId(id);
        condition.setToUserId(AuthUtils.getUser().getId());
        eventMapper.updateByCondition(target, condition);
    }

    /**
     * list
     * @param types
     * @param from
     * @param to
     * @param content
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public Map<String, Object> list(List<Integer> types, String from, String to, String content,String common, String title, Integer pageSize, Integer pageNumber) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        int offset = (pageNumber - 1) * pageSize;
        List<TopicBO> list = eventMapper.list(from, to, content,types,common, title, offset, pageSize);
        Long total = eventMapper.listCount(from, to, content, types,common, title);
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", list);
        map.put("total", total);
        return map;
    }

    /**
     * remove
     * @param map
     */
    public void remove(Map<String, List<Long>> map) {
        List<Long> list = map.get("id");
        if (CollectionUtils.isNotEmpty(list)) {
            eventMapper.batchRemove(list);
        }
    }

    /**
     * 发送系统消息
     * @param eventBO
     */
    public void addSystemMessage(EventBO eventBO) {
        EventDO eventDO = convertBo2Do(eventBO);
        Date createTime = eventDO.getCreateTime();
        eventDO.setType(EventTypeEnum.SYSTEM.getCode());
        long time = createTime.getTime();
        if (time < System.currentTimeMillis()) {
            eventMapper.insert(eventDO);
        } else {
            MessageSchedule.getPool().schedule(() -> eventMapper.insert(eventDO), time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }
}