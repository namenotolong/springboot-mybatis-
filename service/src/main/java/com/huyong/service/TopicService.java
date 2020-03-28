package com.huyong.service;

import com.google.common.collect.Lists;
import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.helper.Sort;
import com.huyong.dao.mapper.ArticleMapper;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.module.TopicBO;
import com.huyong.enums.RelationEnum;
import com.huyong.enums.RoleEnum;
import com.huyong.enums.StatusEnum;
import com.huyong.enums.TopicEnum;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.TopicMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private RelationService relationService;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

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

    /**
     * 获取一个文章下的评论
     * @param articleId
     * @param pageSize
     * @param articleId
     * @return
     */
    public List<TopicBO> getCommons(Long articleId, Integer pageSize, Integer pageNum) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        int offset = (pageNum - 1) * pageSize;
        TopicDO condition = new TopicDO();
        condition.setArticleId(articleId);
        condition.setStatus(StatusEnum.PRESENT.getCode());
        condition.setType(TopicEnum.COMMON.getCode());
        List<TopicBO> commons = topicMapper.getCommonsPageWithUser(condition, offset, pageSize);
        if (CollectionUtils.isEmpty(commons)) {
            return Lists.newArrayList();
        }
        return addParameters(mergeCommons(commons, getReplies(commons)));
    }

    /**
     * 为topic添加附属属性
     * @param topics
     * @return
     */
    private List<TopicBO> addParameters(List<TopicBO> topics) {
        if (CollectionUtils.isNotEmpty(topics)) {
            for (TopicBO topic : topics) {
                //设置话题数目
                topic.setTopicCount(topic.getReplies() == null ? 0L : topic.getReplies().size());
                //设置点赞数目
                topic.setPraiseCount(relationService.getRelationCount(topic.getId(), RelationEnum.BY_PRAISE_TOPIC.getCode()));
                if (AuthUtils.getUser() != null) {
                   //判断当前用户是否点赞
                   topic.setPraised(relationService.checkRelation(AuthUtils.getUser().getId(), topic.getId(), RelationEnum.PRAISE_TOPIC.getCode()));
               }
            }
        }
        return topics;
    }

    /**
     * 获取评论的所有回复
     * @param commons
     * @return
     */
    private List<TopicBO> getReplies(List<TopicBO> commons) {
        List<TopicBO> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(commons)) {
            //获取commons的所有id
            List<Long> commonsId = commons.stream().map(TopicBO::getId).collect(Collectors.toList());
            //获取所有评论的回复
            return topicMapper.getReplies(commonsId);
        }
        return list;
    }

    /**
     * 将评论和回复merge
     * @param commons
     * @param replies
     * @return
     */
    private List<TopicBO> mergeCommons(List<TopicBO> commons, List<TopicBO> replies) {
        List<TopicBO> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(commons)) {
            return list;
        }
        if (CollectionUtils.isEmpty(replies)) {
            return commons;
        }
        for (TopicBO reply : replies) {
            for (TopicBO common : commons) {
                if (reply.getTopicId().equals(common.getId())) {
                    if (common.getReplies() == null) {
                        common.setReplies(Lists.newArrayList());
                    }
                    common.getReplies().add(reply);
                    break;
                }
            }
        }
        return commons;
    }

    /**
     * 发表评论或者回复
     * @param topicBO
     */
    @Transactional(rollbackFor = Exception.class)
    public TopicBO publishTopic(TopicBO topicBO) {
        Integer type = topicBO.getType();
        TopicDO topic = convertBo2Do(topicBO);
        topic.setStatus(StatusEnum.PRESENT.getCode());
        topic.setUserId(AuthUtils.getUser().getId());
        topic.setUpdateUserId(AuthUtils.getUser().getId());
        if (TopicEnum.COMMON.getCode().equals(type)) {
            //评论
            //设置floor
            ArticleDO articleDO = articleMapper.selectByPrimary(topicBO.getArticleId());
            if (articleDO != null) {
                topic.setFloor(articleDO.getFloorCount() + 1);
                articleDO.setFloorCount(articleDO.getFloorCount() + 1);
                articleMapper.updateByPrimary(articleDO);
            }
            topicMapper.insert(topic);
        } else if (TopicEnum.REPLY.getCode().equals(type)) {
            //回复
            if (topicBO.getParentId() == null || topic.getTopicId() == null) {
                throw new CommonException("参数不足！");
            }
            topic.setParentId(topicBO.getParentId());
            topic.setTopicId(topicBO.getTopicId());
            topicMapper.insert(topic);
        }
        return addUserParameter(topic);
    }



    /**
     * 为topic添加user属性
     * @param topic
     * @return
     */
    private TopicBO addUserParameter(TopicDO topic) {
        if (topic == null) {
            return null;
        }
        topic.setUpdateTime(new Date());
        final TopicBO topicBO = convertDo2Bo(topic);
        topicBO.setUser(userService.convertDo2BoOnlySee(userMapper.selectByPrimary(topic.getUserId())));
        return topicBO;
    }

    /**
     * 删除一个评论或者回复,这个文章是当前登录者写的、评论是当前登陆者、
     * 评论的回复是当前登陆者、回复所属评论是当前登陆者
     * @param topicId
     */
    public void delete(Long topicId) {
        TopicDO condition = new TopicDO();
        condition.setStatus(StatusEnum.DELETE.getCode());
        condition.setId(topicId);
        condition.setUpdateUserId(AuthUtils.getUser().getId());
        if (RoleEnum.ADMIN.getCode().equals(AuthUtils.getUser().getRole())) {
            topicMapper.updateByPrimary(condition);
        } else {
            TopicDO topicDO = topicMapper.selectByPrimary(topicId);
            if (topicDO != null) {
                if (!topicDO.getUserId().equals(AuthUtils.getUser().getId())) {
                    //获取回复的评论
                    Long id = topicDO.getTopicId();
                    if (id != null) {
                        TopicDO topic = topicMapper.selectByPrimary(topicId);
                        if (topic.getUserId().equals(AuthUtils.getUser().getId())) {
                            topicMapper.updateByPrimary(condition);
                            return;
                        }
                    }
                    //获取文章
                    ArticleDO articleDO = articleMapper.selectByPrimary(topicDO.getArticleId());
                    if (!articleDO.getUserId().equals(AuthUtils.getUser().getId())) {
                        throw new CommonException("您不能删除别人的言论！");
                    }
                }
                topicMapper.updateByPrimary(condition);
            }
        }
    }

    /**
     * 管理员更新一个评论或者回复
     * @param topicBO
     */
    public void modifyTopic(TopicBO topicBO) {
        topicMapper.updateByPrimary(convertBo2Do(topicBO));
    }

    public List<TopicBO> getUserTopics(Long userId, Integer pageSize, Integer pageNum) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        int offset = (pageNum - 1) * pageSize;
        TopicDO condition = new TopicDO();
        condition.setStatus(StatusEnum.PRESENT.getCode());
        condition.setUserId(userId);
        return topicMapper.getCommonsPageWithUser(condition, offset, pageSize);
    }
}