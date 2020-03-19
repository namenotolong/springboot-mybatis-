package com.huyong.service;

import com.huyong.dao.entity.BarDO;
import com.huyong.dao.module.BarBO;
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
}