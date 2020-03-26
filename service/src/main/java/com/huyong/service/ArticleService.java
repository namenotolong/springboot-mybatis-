package com.huyong.service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.mapper.KindMapper;
import com.huyong.dao.mapper.TopicMapper;
import com.huyong.dao.module.ArticleBO;
import com.huyong.dao.module.KindBO;
import com.huyong.dao.module.UserBO;
import com.huyong.enums.*;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.ArticleMapper;

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
    private TopicService topicService;
    @Resource
    private TopicMapper topicMapper;

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
    public void publishOrModify(ArticleBO articleBO) {
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
        TopicDO condition = new TopicDO();
        condition.setArticleId(id);
        articleBO.setTopicCount((long) topicMapper.count(condition));
    }

    /**
     * 获取文章详细信息，包括用户信息、分类信息
     * @param id
     * @return
     */
    public ArticleBO detail(Long id) {
        ArticleBO detail = articleMapper.detail(id);
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
}
