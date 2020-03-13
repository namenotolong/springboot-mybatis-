package com.huyong.dao.helper;

import com.huyong.exception.DAOException;

import java.util.List;

/**
 * 描述: mapper method template
 * 提供按照主键的增、删、改、查，按条件查询，以及分页查询（可设置多个字段->sort集合）
 *
 * @author huyong
 * @date 2020-03-13 10:20 下午
 */
public interface GenerateMapper<E> {
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
    E getByPrimary(long primary) throws DAOException;

    /**
     * 根据主键更新数据
     *
     * @param e
     *            新数据
     */
    int updateByPrimary(E e) throws DAOException;

    /**
     * 根据主键删除数据
     *
     * @param primary
     *            主键值
     * @return 影响条数
     */
    int deleteByPrimary(long primary) throws DAOException;

    /**
     * 按条件查询
     *
     * @param condition
     * @return
     * @throws DAOException
     */
    List<E> queryByCondition(E condition) throws DAOException;

    /**
     * 按条件查询
     *
     * @param condition
     * @throws DAOException
     */
    List<E> queryByCondition(E condition, List<Sort> sort) throws DAOException;


    /**
     * 统计数据
     *
     * @param condition
     * @return
     * @throws DAOException
     */
    int count(E condition) throws DAOException;

    /**
     * 按条件分页查询
     *
     * @param condition
     * @param offset 偏移量
     * @param size 记录数
     * @param sort 排序
     * @return
     * @throws DAOException
     */
    List<E> queryByPage(E condition, int offset, int size, List<Sort> sort) throws DAOException;

    /**
     * 条件查询，如果是多条记录，返回第一条，加排序条件
     *
     * @param condition
     * @param sort
     * @return
     * @throws DAOException
     */
    E getOne(E condition, List<Sort> sort) throws DAOException;

    /**
     * 条件查询，如果是多条记录，返回第一条，不排序
     *
     * @param condition
     * @param
     * @return
     * @throws DAOException
     */
    E getOne(E condition) throws DAOException;
}
