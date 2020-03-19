package com.huyong.service;

import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.module.ArticleBO;
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
}