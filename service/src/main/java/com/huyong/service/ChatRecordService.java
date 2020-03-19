package com.huyong.service;

import com.huyong.dao.entity.ChatRecordDO;
import com.huyong.dao.module.ChatRecordBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.huyong.dao.mapper.ChatRecordMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: ChatRecordService
 *
 * @author huyong
 * @date 2020-03-19 20:38
 */
@Service
public class ChatRecordService {
    @Resource
    private ChatRecordMapper chatRecordMapper;

    public ChatRecordDO convertBo2Do(ChatRecordBO chatRecord) {
        ChatRecordDO chatRecordDO = new ChatRecordDO();
        BeanUtils.copyProperties(chatRecord, chatRecordDO);
        return chatRecordDO;
    }
    public List<ChatRecordDO> convertBos2Dos(List<ChatRecordBO> chatRecords) {
        if (null == chatRecords || chatRecords.isEmpty()) {
            return new ArrayList<>();
        }
        return chatRecords.stream().map(this::convertBo2Do).collect(Collectors.toList());
    }

    public ChatRecordBO convertDo2Bo(ChatRecordDO chatRecord) {
        ChatRecordBO chatRecordBO = new ChatRecordBO();
        BeanUtils.copyProperties(chatRecord, chatRecordBO);
        return chatRecordBO;
    }
    public List<ChatRecordBO> convertDos2Bos(List<ChatRecordDO> chatRecords) {
        if (null == chatRecords || chatRecords.isEmpty()) {
            return new ArrayList<>();
        }
        return chatRecords.stream().map(this::convertDo2Bo).collect(Collectors.toList());
    }
}