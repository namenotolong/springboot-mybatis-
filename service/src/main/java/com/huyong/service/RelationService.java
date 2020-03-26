package com.huyong.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huyong.dao.entity.RelationDO;
import com.huyong.dao.module.RelationBO;
import com.huyong.enums.RelationEnum;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.RelationMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述: RelationService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class RelationService {
    @Resource
    private RelationMapper relationMapper;

    public RelationDO convertBo2Do(RelationBO relation) {
        RelationDO relationDO = new RelationDO();
        BeanUtils.copyProperties(relation, relationDO);
        return relationDO;
    }
    public List<RelationDO> convertBos2Dos(List<RelationBO> relations) {
        if (null == relations || relations.isEmpty()) {
            return new ArrayList<>();
        }
        return relations.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public RelationBO convertDo2Bo(RelationDO relation) {
        RelationBO relationBO = new RelationBO();
        BeanUtils.copyProperties(relation, relationBO);
        return relationBO;
    }
    public List<RelationBO> convertDos2Bos(List<RelationDO> relations) {
        if (null == relations || relations.isEmpty()) {
            return new ArrayList<>();
        }
        return relations.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }

    /**
     * 判断一个用户是否被当前登录用户关注,文章是否被当前用户收藏
     * @param id
     * @return
     */
    public Map<String, Boolean> getRelations(Long id, Long articleId) {
        Map<String, Boolean> relations = Maps.newHashMap();
        relations.put("praise", checkRelation(AuthUtils.getUser().getId(), id, RelationEnum.PRAISE.getCode()));
        relations.put("store", checkRelation(AuthUtils.getUser().getId(), articleId, RelationEnum.STORE.getCode()));
        relations.put("follow", checkRelation(AuthUtils.getUser().getId(), id, RelationEnum.FOLLOW.getCode()));
        relations.put("praiseArticle", checkRelation(AuthUtils.getUser().getId(), articleId, RelationEnum.PRAISE_ARTICLE.getCode()));
        return relations;
    }
    /**
     * 判断一个用户的type关系列表中是否含有two
     * @param one
     * @param two
     * @param type
     * @return
     */
    private boolean checkRelation(Long one, Long two, Integer type) {
        RelationDO relation = new RelationDO();
        relation.setType(type);
        relation.setOneId(one);
        List<RelationDO> relationDOS = relationMapper.queryByCondition(relation);
        if (CollectionUtils.isNotEmpty(relationDOS))  {
            String othersId = relationDOS.get(0).getOthersId();
            if (StringUtils.isNotBlank(othersId)) {
                List list = JSONObject.parseObject(othersId, List.class);
                return contains(list, two);
            }
        }
        return false;
    }

    /**
     * 检测一个集合中是否存在这个long型变量
     * @param list
     * @param value
     * @return
     */
    private boolean contains(List list, Long value) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Object o : list) {
                if (Long.valueOf(String.valueOf(o)).equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 在一个object集合中移除一个long型变量
     * @param list
     * @param value
     */
    private void remove(List list, Long value) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Object o : list) {
                if (Long.valueOf(String.valueOf(o)).equals(value)) {
                    list.remove(0);
                    break;
                }
            }
        }
    }
    /**
     * one 关注或者取消关注 two
     * @param one
     * @param two
     * @param ops
     */
    @Transactional(rollbackFor = Exception.class)
    public synchronized void follow(Long one, Long two, Integer ops) {
        //在one的关注表中操作two
        opsRelation(one, two, ops, RelationEnum.FOLLOW.getCode());
        //在two的粉丝表中操作one
        opsRelation(two, one, ops, RelationEnum.FAN.getCode());
    }
    @Transactional(rollbackFor = Exception.class)
    public synchronized void praise(Long one, Long two, Integer ops) {
        //在one的点赞表中操作two
        opsRelation(one, two, ops, RelationEnum.PRAISE.getCode());
        //在two的被点赞表中操作one
        opsRelation(two, one, ops, RelationEnum.BY_PRAISE.getCode());
    }
    @Transactional(rollbackFor = Exception.class)
    public synchronized void praiseArticle(Long one, Long two, Integer ops) {
        if (null == two) {
            throw new CommonException("文章id为空！");
        }
        //在one的点赞表中操作two
        opsRelation(one, two, ops, RelationEnum.PRAISE_ARTICLE.getCode());
        //在two的被点赞表中操作one
        opsRelation(two, one, ops, RelationEnum.BY_PRAISE_ARTICLE.getCode());
    }
    @Transactional(rollbackFor = Exception.class)
    public synchronized void store(Long one, Long two, Integer ops) {
        if (null == two) {
            throw new CommonException("文章id为空！");
        }
        //在one的收藏表中操作two
        opsRelation(one, two, ops, RelationEnum.STORE.getCode());
        //在two的被收藏表中操作one
        opsRelation(two, one, ops, RelationEnum.BY_STORE.getCode());
    }
    /**
     * 在one的 关系 表中 添加/删除 two
     * @param one
     * @param two
     * @param ops
     * @param type
     */
    private void opsRelation(Long one, Long two, Integer ops, Integer type) {
        //获取one的type关系表
        RelationDO condition = new RelationDO();
        condition.setOneId(one);
        condition.setType(type);
        List<RelationDO> follows = relationMapper.queryByCondition(condition);
        if (CollectionUtils.isEmpty(follows)) {
            //如果是删除  为空则直接退出
            if (ops == 1) {
                return;
            }
            //添加
            RelationDO followTemp = new RelationDO();
            followTemp.setType(type);
            followTemp.setOneId(one);
            followTemp.setOthersId(Collections.singletonList(two).toString());
            followTemp.setCount(1L);
            relationMapper.insert(followTemp);
        } else {
            final RelationDO relationDO = follows.get(0);
            String followString = relationDO.getOthersId();
            List followList = JSONObject.parseObject(followString, List.class);
            //添加
            if (ops == 0) {
                if (CollectionUtils.isEmpty(followList)) {
                    followList = Lists.newArrayList();
                }
                if (contains(followList, two)) {
                    return;
                }
                followList.add(two);
                relationDO.setCount(relationDO.getCount() + 1);
            } else {
                //删除
                if (CollectionUtils.isNotEmpty(followList)) {
                    if (!contains(followList, two)) {
                        return;
                    }
                    remove(followList, two);
                    relationDO.setCount(relationDO.getCount() - 1);
                }
            }
            relationDO.setOthersId(followList.toString());
            relationMapper.updateByPrimary(relationDO);
        }
    }

    /**
     * 获取各个关系的数量
     * @return
     */
    public Map<String, Long> getRelationMapCount(Long userId, Long articleId) {
        Map<String, Long> relations = Maps.newHashMap();
        relations.put("byPraise", getRelationCount(userId, RelationEnum.BY_PRAISE.getCode()));
        relations.put("byStore", getRelationCount(articleId, RelationEnum.BY_STORE.getCode()));
        relations.put("fans", getRelationCount(userId, RelationEnum.FAN.getCode()));
        return relations;
    }

    /**
     * 获取type关系的 one->id count
     * @param id
     * @param type
     * @return
     */
    public Long getRelationCount(Long id, Integer type) {
        RelationDO relationDO = new RelationDO();
        relationDO.setOneId(id);
        relationDO.setType(type);
        List<RelationDO> relations = relationMapper.queryByCondition(relationDO);
        return relations.isEmpty() ? 0 : relations.get(0).getCount();
    }

    /**
     * 更改关系状态 -> 当前登陆用户
     * @param userId
     * @param articleId
     * @param ops  0：添加  1：删除
     * @param type
     */
    public void modifyRelation(Long userId, Long articleId, Integer ops, Integer type) {
        switch (type) {
            //点赞用户
            case 1 : praise(AuthUtils.getUser().getId(), userId, ops);break;
            //收藏文章
            case 2 : store(AuthUtils.getUser().getId(), articleId, ops);break;
            //关注用户
            case 3 : follow(AuthUtils.getUser().getId(), userId, ops);break;
            //点赞文章
            case 4 : praiseArticle(AuthUtils.getUser().getId(), articleId, ops);break;
            default:break;
        }
    }
}