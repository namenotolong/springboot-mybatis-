package com.huyong.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.ArticleMapper;
import com.huyong.dao.mapper.TopicMapper;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.enums.StatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 描述: dashboard
 *
 * @author huyong
 * @date 2020-04-05 4:29 下午
 */
@Service
public class DashboardService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TopicMapper topicMapper;
    /**
     * 用户数量、文章数量、消息数量
     * @return
     */
    public List<Long> getGrid() {
        List<Long> list = Lists.newArrayList();
        //访问量
        list.add(articleMapper.getVisitCount());
        //用户数量
        UserDO condition = new UserDO();
        condition.setStatus(StatusEnum.PRESENT.getCode());
        list.add((long) userMapper.count(condition));
        //文章数量
        ArticleDO articleDO = new ArticleDO();
        articleDO.setStatus(StatusEnum.PRESENT.getCode());
        list.add((long) articleMapper.count(articleDO));
        //消息
        TopicDO topicDO = new TopicDO();
        topicDO.setStatus(StatusEnum.PRESENT.getCode());
        list.add((long) topicMapper.count(topicDO));
        return list;
    }

    /**
     * 获取访问量、用户数量、文章数量、消息数量最近七天或者一个月的变化
     * @param type
     * @return
     */
    public Map<String, List<Map<String, Long>>> lineChart(String type) {
        Map<String, List<Map<String, Long>>> map = Maps.newHashMap();
        String[] arr = {"visit", "user", "article", "message"};
        int count = 30;
        if ("week".equals(type)) {
            count = 7;
        }
        List<Long> grid = getGrid();
        for (int i = 0; i < arr.length; i++) {
            Long visit = grid.get(i);
            if(visit == null) {
                visit = 0L;
            }
            List<Map<String, Long>> list = Lists.newArrayList();
            for (int j = 0; j < count; j++) {
                Map<String, Long> item = Maps.newHashMap();
                item.put("value", visit);
                visit /= 2;
                list.add(item);
            }
            Collections.reverse(list);
            map.put(arr[i], list);
        }
        return map;
    }
}
