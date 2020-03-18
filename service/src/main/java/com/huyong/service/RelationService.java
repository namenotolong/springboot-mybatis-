package com.huyong.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.RelationMapper;

/**
 * 描述: RelationService
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Service
public class RelationService {
    @Resource
    private RelationMapper relationMapper;
}