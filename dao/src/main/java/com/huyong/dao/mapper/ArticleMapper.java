package com.huyong.dao.mapper;

import com.huyong.dao.entity.ArticleDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.ArticleBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: ArticleDO对应的Mapper
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Repository
public interface ArticleMapper extends BaseMapper<ArticleDO> {
    /**
     * 获取文章列表
     * @param offset
     * @param pageNum
     * @param kindId
     * @param userId
     * @param type
     * @return
     */
    List<ArticleBO> getArticles(@Param("offset") Long offset, @Param("pageNum") Long pageNum, @Param("kindId") Integer kindId, @Param("userId") Long userId,@Param("type") Integer type);
}