package com.huyong.service;

import com.huyong.dao.entity.RelationDO;
import com.huyong.dao.module.RelationBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.RelationMapper;

import java.util.ArrayList;
import java.util.List;
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
}