package com.huyong.dao.mapper;

import com.huyong.dao.entity.UserDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.UserBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: UserDO对应的Mapper
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 通过id集合批量获取数据
     * @param list
     * @return
     */
    List<UserBO> getListUserByIds(@Param("list") List list);

    /**
     * 搜索
     * @param key
     * @param offset
     * @param pageSize
     * @return
     */
    List<UserBO> search(@Param("key") String key,@Param("offset") int offset,@Param("pageSize") Integer pageSize);

    /**
     * 获取总数
     * @param key
     * @return
     */
    int searchCount(@Param("key") String key);
}