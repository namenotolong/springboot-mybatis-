package com.huyong.service;

import com.huyong.dao.entity.KindDO;
import com.huyong.dao.module.KindBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.KindMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: KindService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class KindService {
    @Resource
    private KindMapper kindMapper;

    public KindDO convertBo2Do(KindBO kind) {
        KindDO kindDO = new KindDO();
        BeanUtils.copyProperties(kind, kindDO);
        return kindDO;
    }
    public List<KindDO> convertBos2Dos(List<KindBO> kinds) {
        if (null == kinds || kinds.isEmpty()) {
            return new ArrayList<>();
        }
        return kinds.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public KindBO convertDo2Bo(KindDO kind) {
        KindBO kindBO = new KindBO();
        BeanUtils.copyProperties(kind, kindBO);
        return kindBO;
    }
    public List<KindBO> convertDos2Bos(List<KindDO> kinds) {
        if (null == kinds || kinds.isEmpty()) {
            return new ArrayList<>();
        }
        return kinds.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }
}