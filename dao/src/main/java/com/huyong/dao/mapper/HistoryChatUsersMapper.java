package com.huyong.dao.mapper;

import com.huyong.dao.entity.HistoryChatUsersDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.HistoryChatUsersBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: HistoryChatUsersDO对应的Mapper
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Repository
public interface HistoryChatUsersMapper extends BaseMapper<HistoryChatUsersDO> {
    /**
     * 获取历史聊天用户
     * @param id
     * @return
     */
    List<HistoryChatUsersBO> getUsers(@Param("id") Long id);
}