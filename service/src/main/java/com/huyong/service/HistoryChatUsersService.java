package com.huyong.service;

import com.huyong.dao.entity.HistoryChatUsersDO;
import com.huyong.dao.module.HistoryChatUsersBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.HistoryChatUsersMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: HistoryChatUsersService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class HistoryChatUsersService {
    @Resource
    private HistoryChatUsersMapper historyChatUsersMapper;

    public HistoryChatUsersDO convertBo2Do(HistoryChatUsersBO historyChatUsers) {
        HistoryChatUsersDO historyChatUsersDO = new HistoryChatUsersDO();
        BeanUtils.copyProperties(historyChatUsers, historyChatUsersDO);
        return historyChatUsersDO;
    }
    public List<HistoryChatUsersDO> convertBos2Dos(List<HistoryChatUsersBO> historyChatUserss) {
        if (null == historyChatUserss || historyChatUserss.isEmpty()) {
            return new ArrayList<>();
        }
        return historyChatUserss.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public HistoryChatUsersBO convertDo2Bo(HistoryChatUsersDO historyChatUsers) {
        HistoryChatUsersBO historyChatUsersBO = new HistoryChatUsersBO();
        BeanUtils.copyProperties(historyChatUsers, historyChatUsersBO);
        return historyChatUsersBO;
    }
    public List<HistoryChatUsersBO> convertDos2Bos(List<HistoryChatUsersDO> historyChatUserss) {
        if (null == historyChatUserss || historyChatUserss.isEmpty()) {
            return new ArrayList<>();
        }
        return historyChatUserss.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }
}