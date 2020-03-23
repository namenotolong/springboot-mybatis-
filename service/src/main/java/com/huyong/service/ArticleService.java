package com.huyong.service;

import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.module.ArticleBO;
import com.huyong.dao.module.UserBO;
import com.huyong.enums.OpsEnum;
import com.huyong.enums.RoleEnum;
import com.huyong.enums.StatusEnum;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
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
}
