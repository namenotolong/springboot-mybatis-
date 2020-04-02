package com.huyong.service;

import com.huyong.dao.entity.HistoryChatUsersDO;
import com.huyong.dao.module.HistoryChatUsersBO;
import com.huyong.enums.OpsEnum;
import com.huyong.enums.StatusEnum;
import com.huyong.utils.AuthUtils;
import org.apache.commons.collections.CollectionUtils;
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

    /**
     * 获取历史聊天用户
     * @return
     */
    public List<HistoryChatUsersBO> getUsers() {
        return historyChatUsersMapper.getUsers(AuthUtils.getUser().getId());
    }

    /**
     * 新增一个
     * @param id
     */
    public void add(Long id) {
        HistoryChatUsersDO condition = new HistoryChatUsersDO();
        condition.setStatus(StatusEnum.PRESENT.getCode());
        condition.setUserId(AuthUtils.getUser().getId());
        condition.setCustomer(id);
        final List<HistoryChatUsersDO> users = historyChatUsersMapper.queryByCondition(condition);
        if (CollectionUtils.isEmpty(users)) {
            historyChatUsersMapper.insert(condition);
        }
    }

    public void remove(Long id) {
        HistoryChatUsersDO condition = new HistoryChatUsersDO();
        condition.setId(id);
        condition.setStatus(StatusEnum.DELETE.getCode());
        historyChatUsersMapper.updateByPrimary(condition);
    }
}