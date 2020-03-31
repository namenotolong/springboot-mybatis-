package com.huyong.dao.mapper;

import com.huyong.dao.entity.EventDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.EventBO;
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
}