package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.ArticleMapper;

/**
 * 描述: ArticleService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
}