package com.huyong.service;

import com.google.common.collect.Lists;
import com.huyong.dao.entity.BarDO;
import com.huyong.dao.module.BarBO;
import com.huyong.enums.BarTypeEnum;
import com.huyong.enums.StatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.BarMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: BarService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class BarService {
    @Resource
    private BarMapper barMapper;

    public BarDO convertBo2Do(BarBO bar) {
        BarDO barDO = new BarDO();
        BeanUtils.copyProperties(bar, barDO);
        return barDO;
    }
    public List<BarDO> convertBos2Dos(List<BarBO> bars) {
        if (null == bars || bars.isEmpty()) {
            return new ArrayList<>();
        }
        return bars.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public BarBO convertDo2Bo(BarDO bar) {
        BarBO barBO = new BarBO();
        BeanUtils.copyProperties(bar, barBO);
        return barBO;
    }
    public List<BarBO> convertDos2Bos(List<BarDO> bars) {
        if (null == bars || bars.isEmpty()) {
            return new ArrayList<>();
        }
        return bars.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }

    public List<BarBO> getLeft() {
        List<BarBO> bars = getBars(BarTypeEnum.LEFT.getCode());
        return CollectionUtils.isEmpty(bars) ? Lists.newArrayList() : convertToTree(bars);
    }

    /**
     * 变为二级菜单
     * @return
     */
    public List<BarBO> convertToTree(List<BarBO> bars) {
        List<BarBO> lists = Lists.newArrayList();
        bars.forEach(x -> {
            if (x.getParentId() == null) {
                lists.add(x);
            }
        });
        bars.forEach(x -> {
            if (x.getParentId() != null) {
                lists.forEach(y -> {
                    if (y.getId().equals(x.getParentId())) {
                        if (y.getChildren() == null) {
                            y.setChildren(Lists.newArrayList());
                        }
                        y.getChildren().add(x);
                    }
                });
            }
        });
        return lists;
    }


    public List<BarBO> getRight() {
        List<BarBO> bars = getBars(BarTypeEnum.RIGHT.getCode());
        return CollectionUtils.isEmpty(bars) ? Lists.newArrayList() : bars;
    }

    /**
     * 获取导航列表
     * @param type
     * @return
     */
    private List<BarBO> getBars(Integer type) {
        BarDO barDO = new BarDO();
        barDO.setStatus(StatusEnum.PRESENT.getCode());
        barDO.setType(type);
        List<BarDO> bars = barMapper.queryByCondition(barDO);
        return convertDos2Bos(bars);
    }
}