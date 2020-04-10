package com.huyong.dao.mapper;

import com.huyong.dao.entity.EventDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.EventBO;
import com.huyong.dao.module.TopicBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: EventDO对应的Mapper
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Repository
public interface EventMapper extends BaseMapper<EventDO> {
    /**
     * 根据type获取eventBO
     * @param offset
     * @param pageSize
     * @param type
     * @param id
     * @return
     */
    List<EventBO> getEventBosByTypeWithPageOrderByTime(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize,@Param("type") Integer type, @Param("id") Long id);

    /**
     * 获取type的总数
     * @param type
     * @param id
     * @return
     */
    int getEventBosByTypeCount(@Param("type") Integer type, @Param("id") Long id);

    /**
     * 获取聊天列表
     * @param id
     * @return
     */
    List<EventBO> getChats(@Param("id") Long id);

    /**
     * 获取两个用户的聊天记录
     * @param from
     * @param to
     * @return
     */
    List<EventBO> getRecord(@Param("from") Long from,@Param("to") Long to);

    /**
     * list
     * @param from
     * @param to
     * @param content
     * @param types
     * @param common
     * @param title
     * @param offset
     * @param pageSize
     * @return
     */
    List<TopicBO> list(@Param("from") String from,@Param("to") String to,@Param("content") String content,@Param("types") List<Integer> types,@Param("common") String common,@Param("title") String title,@Param("offset") int offset,@Param("pageSize") Integer pageSize);

    /**
     * count
     * @param from
     * @param to
     * @param content
     * @param types
     * @param common
     * @param title
     * @return
     */
    Long listCount(@Param("from") String from,@Param("to") String to,@Param("content") String content,@Param("types") List<Integer> types,@Param("common") String common,@Param("title") String title);
    /**
     * batch remove
     * @param list
     */
    void batchRemove(@Param("list") List<Long> list);
}