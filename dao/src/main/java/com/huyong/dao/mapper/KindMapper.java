package com.huyong.dao.mapper;

import com.huyong.dao.entity.KindDO;
import com.huyong.dao.helper.BaseMapper;
import com.huyong.dao.module.KindBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: KindDO对应的Mapper
 *
 * @author huyong
 * @date 2020-03-19 02:28
 */
@Repository
public interface KindMapper extends BaseMapper<KindDO> {
    /**
     * 通过id集合获取分类
     * @return
     * @param kindIdList
     */
    List<KindBO> getKindsByIds(@Param("kindIdList") List<String> kindIdList);
}