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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public void test() {
        File file = new File("/Users/weidian/Documents/huyong/huyong-boke-front/springboot-mybatis-/huyong-boke-front/input.txt");
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<BarDO> list = Lists.newArrayList();
            bufferedReader.lines().forEach(x -> {
                BarDO bar = new BarDO();
                bar.setContent(x.trim());
                list.add(bar);
            });
            barMapper.batchInsert(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<BarDO> getAll() {
        return barMapper.queryByCondition(new BarDO());
    }
}