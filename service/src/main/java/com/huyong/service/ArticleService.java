package com.huyong.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.huyong.common.PageTemp;
import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.entity.RelationDO;
import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.mapper.*;
import com.huyong.dao.module.ArticleBO;
import com.huyong.dao.module.KindBO;
import com.huyong.dao.module.UserBO;
import com.huyong.enums.*;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: ArticleService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private KindMapper kindMapper;
    @Resource
    private RelationService relationService;
    @Resource
    private RelationMapper relationMapper;
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

    public ArticleDO convertBo2Do(ArticleBO article) {
        ArticleDO articleDO = new ArticleDO();
        BeanUtils.copyProperties(article, articleDO);
        return articleDO;
    }
    public List<ArticleDO> convertBos2Dos(List<ArticleBO> articles) {
        if (null == articles || articles.isEmpty()) {
            return new ArrayList<>();
        }
        return articles.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public ArticleBO convertDo2Bo(ArticleDO article) {
        ArticleBO articleBO = new ArticleBO();
        BeanUtils.copyProperties(article, articleBO);
        return articleBO;
    }
    public List<ArticleBO> convertDos2Bos(List<ArticleDO> articles) {
        if (null == articles || articles.isEmpty()) {
            return new ArrayList<>();
        }
        return articles.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }

    /**
     * 普通用户只能对标题、分类、内容、类型进行更改
     * @param articleBO
     * @return
     */
    private ArticleDO getUpdateOrInsertCommonArticle(ArticleBO articleBO) {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setTitle(articleBO.getTitle());
        articleDO.setContent(articleBO.getContent());
        articleDO.setKindIds(articleBO.getKindIds());
        articleDO.setType(articleBO.getType());
        articleDO.setId(articleBO.getId());
        articleDO.setUserId(AuthUtils.getUser().getId());
        articleDO.setStatus(StatusEnum.PRESENT.getCode());
        return articleDO;
    }

    /**
     * 发布文章
     * @param articleBO
     */
    public ArticleDO publishOrModify(ArticleBO articleBO) {
        Integer ops = articleBO.getOps();
        ArticleDO articleDO = getUpdateOrInsertCommonArticle(articleBO);
        ArticleDO admin = convertBo2Do(articleBO);
        UserBO user = AuthUtils.getUser();
        admin.setUserId(user.getId());
        admin.setStatus(StatusEnum.PRESENT.getCode());
        if (ops.equals(OpsEnum.INSERT.getCode())) {
            //如果是管理员直接新增全部元素
            if (user.getRole().equals(RoleEnum.ADMIN.getCode())) {
                articleMapper.insert(admin);
            } else {
                articleMapper.insert(articleDO);
            }
        } else {
            if (articleBO.getId() == null) {
                throw new CommonException("id参数为空！");
            }
            if (user.getRole().equals(RoleEnum.ADMIN.getCode())) {
                articleMapper.updateByPrimary(admin);
            } else {
                //检测是否是本人修改
                ArticleDO previous = articleMapper.selectByPrimary(articleBO.getId());
                if (previous == null || !previous.getUserId().equals(user.getId())) {
                    throw new CommonException("不能修改他人文章！");
                }
                articleMapper.updateByPrimary(articleDO);
            }
        }
        return articleDO;
    }

    /**
     * 获取文章列表
     * @param pageSize
     * @param pageNum
     * @param kindId
     * @param userId
     * @param type
     * @return
     */
    public List<ArticleBO> getArticles(Long pageSize, Long pageNum, Integer kindId, Long userId, Integer type) {
        if (pageSize < 1) {
            pageSize = 1L;
        }
        if (pageNum < 1) {
            pageNum = 1L;
        }
        Long offset = (pageNum - 1) * pageSize;
        if (type == null) {
            type = ArticleTypeEnum.TEXT.getCode();
        }
        final List<ArticleBO> articles = articleMapper.getArticles(offset, pageSize, kindId, userId, type);
        articles.forEach(this :: addParameter);
        return articles;
    }

    /**
     * 添加各项count
     * @param articleBO
     */
    private void addParameter(ArticleBO articleBO) {
        Long id = articleBO.getId();
        articleBO.setPraiseCount(relationService.getRelationCount(id, RelationEnum.BY_PRAISE_ARTICLE.getCode()));
        articleBO.setStoreCount(relationService.getRelationCount(id, RelationEnum.BY_STORE.getCode()));
        Long count = topicMapper.getTopicCount(id);
        articleBO.setTopicCount(count);
    }

    /**
     * 获取文章详细信息，包括用户信息、分类信息
     * @param id
     * @return
     */
    public ArticleBO detail(Long id) {
        ArticleBO detail = articleMapper.detail(id);
        if (detail == null) {
            return new ArticleBO();
        }
        String kindIds = detail.getKindIds();
        //添加分类信息
        if (StringUtils.isNotBlank(kindIds)) {
            final Iterable<String> split = Splitter
                    .on(',')
                    .omitEmptyStrings()
                    .trimResults()
                    .split(kindIds);
            List<String> kindIdList = Lists.newArrayList();
            split.forEach(kindIdList :: add);
            List<KindBO> kinds = kindMapper.getKindsByIds(kindIdList);
            detail.setKinds(kinds);
        }
        //添加各项count
        addParameter(detail);
        return detail;
    }

    public void addVisit(Long id) {
        Long userId = AuthUtils.getUser() == null ? null : AuthUtils.getUser().getId();
        log.info("[{}]浏览了[{}]文章", userId, id);
        final ArticleDO articleDO = articleMapper.selectByPrimary(id);
        if (articleDO == null) {
            throw new CommonException("该文章不存在！");
        }
        articleDO.setVisitCount(articleDO.getVisitCount() + 1);
        articleMapper.updateByPrimary(articleDO);
    }

    public List<ArticleBO> getStoreArticles() {
        //获取关注的文章id集合
        RelationDO condition = new RelationDO();
        condition.setOneId(AuthUtils.getUser().getId());
        condition.setType(RelationEnum.STORE.getCode());
        List<RelationDO> relationDOS = relationMapper.queryByCondition(condition);
        if (CollectionUtils.isNotEmpty(relationDOS)) {
            final String othersId = relationDOS.get(0).getOthersId();
            if (StringUtils.isNotBlank(othersId)) {
                final List list = JSONObject.parseObject(othersId, List.class);
                if (CollectionUtils.isNotEmpty(list)) {
                    List<ArticleBO> articles = articleMapper.getArticlesByIds(list);
                    if (CollectionUtils.isNotEmpty(articles)) {
                        articles.forEach(this::addParameter);
                    }
                    return articles;
                }
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 删除一个文章
     * @param id
     */
    public void deleteArticle(Long id) {
        boolean admin = AuthUtils.getUser().getRole().equals(RoleEnum.ADMIN.getCode());
        ArticleDO condition = new ArticleDO();
        condition.setId(id);
        condition.setStatus(StatusEnum.DELETE.getCode());
        if (admin) {
            articleMapper.updateByPrimary(condition);
            deleteTopicsOfArticle(id);
        } else {
            final ArticleDO articleDO = articleMapper.selectByPrimary(id);
            if (null != articleDO && articleDO.getUserId().equals(AuthUtils.getUser().getId())) {
                articleMapper.updateByPrimary(condition);
                deleteTopicsOfArticle(id);
            }
        }
    }

    /**
     * 删除一个文章的所有话题
     * @param id
     */
    public void deleteTopicsOfArticle(Long id) {
        TopicDO condition = new TopicDO();
        condition.setArticleId(id);
        TopicDO target = new TopicDO();
        target.setStatus(StatusEnum.DELETE.getCode());
        topicMapper.updateByCondition(target, condition);
    }

    public List<ArticleBO> getFollowsArticles() {
        final List others = relationService.getOthers(AuthUtils.getUser().getId(), RelationEnum.FOLLOW.getCode());
        if (CollectionUtils.isNotEmpty(others)) {
            return articleMapper.getArticlesByUserIds(others);
        }
        return Lists.newArrayList();
    }

    /**
     * 搜索文章或者用户
     * @param key
     * @param pageSize
     * @param pageSize
     * @param type
     * @return
     */
    public PageTemp<List> search(String key, Integer pageSize, Integer pageNum, Integer type) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        int offset = (pageNum - 1) * pageSize;
        if (type == null) {
            type = ArticleTypeEnum.TEXT.getCode();
        }
        if (type == 2) {
            //文章
            List<ArticleBO> articles =  articleMapper.search(key, offset, pageSize);
            articles.forEach(this :: addParameter);
            return new PageTemp(articles, pageSize, pageNum, articleMapper.searchCount(key));
        } else if (type == 1) {
            //用户
            List<UserBO> users =  userMapper.search(key, offset, pageSize);
            return new PageTemp(users, pageSize, pageNum, userMapper.searchCount(key));
        }
        return new PageTemp<>();
    }
}
