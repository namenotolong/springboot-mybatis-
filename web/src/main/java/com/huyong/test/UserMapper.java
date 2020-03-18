package com.huyong.test;

import com.huyong.dao.entity.UserDO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 获取所有user
     *
     * @return 所有user
     */

    @Select(value = "select * from `user`")
    List<UserDO> getAllUser();

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    @Select(value = "select * from `user` where id = #{id}")
    UserDO getUserById(Integer id);
}
