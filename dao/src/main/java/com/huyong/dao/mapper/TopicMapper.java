package com.huyong.dao.mapper;

import com.huyong.dao.entity.TopicDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.TopicBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: TopicDO对应的Mapper
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Repository
public interface TopicMapper extends BaseMapper<TopicDO> {
    /**
     * 获取评论列表的回复
     * @param commonsId
     * @return
     */
    List<TopicBO> getReplies(@Param("commonsId") List<Long> commonsId);

    /**
     * 获取评论携带用户信息并分页
     * @param condition
     * @param offset
     * @param pageSize
     * @return
     */
    List<TopicBO> getCommonsPageWithUser(@Param("condition") TopicDO condition,@Param("offset") int offset,@Param("pageSize") Integer pageSize);

    /**
     * 获取一个文章下的topicCount
     * @param id
     * @return
     */
    Long getTopicCount(@Param("id") Long id);
}