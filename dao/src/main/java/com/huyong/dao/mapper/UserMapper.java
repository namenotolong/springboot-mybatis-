package com.huyong.dao.mapper;

import com.huyong.dao.entity.UserDO;
import com.huyong.dao.model.UserBO;
import com.sun.tools.javac.util.List;
import org.springframework.stereotype.Repository;

/**
 * 描述: user表Mapper
 *
 * @author huyong
 * @date 2020-02-22 1:21 下午
 */
@Repository
public interface UserMapper {
    /**
     * 查找用户
     * @param user
     * @return
     */
    List<UserDO> queryUsers(UserBO user);
}