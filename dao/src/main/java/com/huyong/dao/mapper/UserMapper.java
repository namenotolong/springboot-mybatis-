package com.huyong.dao.mapper;

import com.huyong.dao.entity.UserDO;
import com.huyong.dao.util.BaseMapper;
import com.huyong.dao.util.GenerateMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 描述: user表Mapper
 *
 * @author huyong
 * @date 2020-02-22 1:21 下午
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
    UserDO get();
    UserDO test();
    UserDO test(Integer id);
    void myInsert(UserDO userDO);
    void insertTest(UserDO userDO);
}

