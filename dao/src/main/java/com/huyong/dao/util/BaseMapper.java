package com.huyong.dao.util;

import com.huyong.exception.DAOException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述: base mapper
 *
 * @author huyong
 * @date 2020-03-14 9:39 下午
 */
public interface BaseMapper<E> {
    /**
     * 插入数据
     * @param e
     *            实体类
     * @return rows , 主键id请通过 e.getId() 获取
     */
    int insert(E e) throws DAOException;

    /**
     * 批量新增数据
     * @param e
     * @return
     * @throws DAOException
     */
    int batchInsert(List<E> e);


    /**
     * 根据主键查询数据
     *
     * @param primary
     *            主键值
     * @return 数据结果
     */
    E selectByPrimary(long primary) throws DAOException;

    /**
     * 根据主键更新数据
     *
     * @param e
     *            新数据
     */
    int updateByPrimary(E e) throws DAOException;

    /**
     * 根据条件更新
     * @param target 更新的内容
     * @param condition
     * @return
     * @throws DAOException
     */
    int updateByCondition(@Param("target") E target, @Param("condition") E condition) throws DAOException;

    /**
     * 根据主键删除数据
     *
     * @param primary
     *            主键值
     * @return 影响条数
     */
    int deleteByPrimary(long primary) throws DAOException;

    /**
     * 根据条件删除
     * @param e
     * @return
     * @throws DAOException
     */
    int deleteByCondition(E e) throws DAOException;

    /**
     * 按条件查询
     *
     * @param condition
     * @return
     * @throws DAOException
     */
    List<E> queryByCondition(E condition);

    /**
     * 排序字段，多个字段order by
     * @param condition
     * @param sorts
     * @return
     * @throws DAOException
     */
    List<E> queryByConditionOrderBySorts(@Param("condition") E condition, @Param("sorts") List<Sort> sorts) throws DAOException;

    /**
     * 单个字段orderBy
     * @param condition
     * @param sort
     * @return
     * @throws DAOException
     */
    List<E> queryByConditionOrderBySort(@Param("condition") E condition, @Param("sort") Sort sort) throws DAOException;


    /**
     * 统计数据
     *
     * @param condition
     * @return
     * @throws DAOException
     */
    int count(E condition) throws DAOException;

    /**
     * 分页查找
     * @param e
     * @param offset
     * @param size
     * @return
     */
    List<E> queryByPage(@Param("condition") E e, @Param("offset") int offset, @Param("size") int size);

    /**
     * 分页  并根据多个字段排序
     * @param e
     * @param offset
     * @param size
     * @param sorts
     * @return
     */
    List<E> queryByPageWithSorts(@Param("condition") E e, @Param("offset") int offset, @Param("size") int size, @Param("sorts") List<Sort> sorts);

    /**
     * 分页  根据单个字段排序
     * @param e
     * @param offset
     * @param size
     * @param sort
     * @return
     */
    List<E> queryByPageWithSort(@Param("condition") E e, @Param("offset") int offset, @Param("size") int size, @Param("sort") Sort sort);
}
