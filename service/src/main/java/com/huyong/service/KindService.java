package com.huyong.service;

import com.google.common.collect.Lists;
import com.huyong.dao.entity.KindDO;
import com.huyong.dao.module.KindBO;
import com.huyong.enums.StatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.KindMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Transactional(rollbackFor = Exception.class)
    public void add() {
        final KindDO kindDO1 = new KindDO();
        kindDO1.setKindName("操作系统");
        kindDO1.setStatus(0);
        kindMapper.insert(kindDO1);
        List<String> list = Arrays.asList("windows", "linux", "mac", "嵌入式");
        List<KindDO> collect = list.stream().map(x -> {
            KindDO kindDO = new KindDO();
            kindDO.setStatus(0);
            kindDO.setKindName(x);
            kindDO.setParentId(kindDO1.getId());
            return kindDO;
        }).collect(Collectors.toList());
        kindMapper.batchInsert(collect);

        final KindDO kindDO2 = new KindDO();
        kindDO2.setKindName("硬件开发");
        kindDO2.setStatus(0);
        kindMapper.insert(kindDO2);
        list = Arrays.asList("自动化", "单片机", "电路设计", "驱动", "硬件", "系统集成");
        List<KindDO> collect1 = list.stream().map(x -> {
            KindDO kindDO = new KindDO();
            kindDO.setStatus(0);
            kindDO.setKindName(x);
            kindDO.setParentId(kindDO2.getId());
            return kindDO;
        }).collect(Collectors.toList());
        kindMapper.batchInsert(collect1);

        final KindDO kindDO3 = new KindDO();
        kindDO3.setKindName("人工智能");
        kindDO3.setStatus(0);
        kindMapper.insert(kindDO3);
        list = Arrays.asList("机器学期", "深度学习", "语音识别", "图像识别", "风控算法", "自然语言");
        List<KindDO> collect2 = list.stream().map(x -> {
            KindDO kindDO = new KindDO();
            kindDO.setStatus(0);
            kindDO.setKindName(x);
            kindDO.setParentId(kindDO3.getId());
            return kindDO;
        }).collect(Collectors.toList());
        kindMapper.batchInsert(collect2);

        final KindDO kindDO4 = new KindDO();
        kindDO4.setKindName("其他分类");
        kindDO4.setStatus(0);
        kindMapper.insert(kindDO4);
        list = Arrays.asList("情感生活", "非技术", "求职面试", "游戏开发", "信息安全", "软件测试");
        List<KindDO> collect3 = list.stream().map(x -> {
            KindDO kindDO = new KindDO();
            kindDO.setStatus(0);
            kindDO.setKindName(x);
            kindDO.setParentId(kindDO4.getId());
            return kindDO;
        }).collect(Collectors.toList());
        kindMapper.batchInsert(collect3);

    }

    public KindDO wrapDO() {
        KindDO kindBO = new KindDO();
        kindBO.setStatus(StatusEnum.PRESENT.getCode());
        return kindBO;
    }

    public static void main(String[] args) {
        new KindService().add();
    }

    public List<KindBO> getKinds() {
        KindDO condition = wrapDO();
        List<KindDO> kindDOS = kindMapper.queryByCondition(condition);
        return convert2Kinds(kindDOS);
    }

    /**
     * 转变为二级目录
     * @return
     */
    public List<KindBO> convert2Kinds(List<KindDO> kinds) {
        if (CollectionUtils.isEmpty(kinds)) {
            return Lists.newArrayList();
        }
        List<KindBO> list = convertDos2Bos(kinds);
        List<KindBO> kindBOS = Lists.newArrayList();
        start:for (int i = 0; i < list.size(); i++) {
            //如果是一级目录 直接插入
            KindBO kind = list.get(i);
            if (kind.getParentId() == null) {
                kindBOS.add(kind);
            } else {
                for (KindBO kindBO : kindBOS) {
                    if (kindBO.getId().equals(kind.getParentId())) {
                        if (kindBO.getChildren() == null) {
                            kindBO.setChildren(Lists.newArrayList());
                        }
                        kindBO.getChildren().add(kind);
                        continue start;
                    }
                }
                //已加入的一级目录里面不存在,则在下面的列表中查找
                for (int j = i + 1; j < list.size(); j++) {
                    final KindBO temp = list.get(j);
                    if (temp.getId().equals(kind.getParentId())) {
                        if (temp.getChildren() == null) {
                            temp.setChildren(Lists.newArrayList());
                        }
                        temp.getChildren().add(kind);
                        continue start;
                    }
                }
            }
        }
        return kindBOS;
    }
}