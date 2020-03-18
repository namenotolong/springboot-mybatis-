package com.huyong.dao.util;

import com.huyong.dao.util.enums.DBType;

/**
 * 描述:组装mapper接口，约定内容
 *
 * @author huyong
 * @date 2020-03-13 8:23 下午
 */
public interface Builder {
    String SEP_COMMA = ",";

    /**
     * 构建输出文件的Header部分
     */
    void buildHeader();

    /**
     * 构建输出文件的Body部分
     */
    void buildBody();

    /**
     * 构建输出文件的Footer部分
     */
    void buildFooter();


    /**
     * 获取构建结果
     * @return
     */
    StringBuilder getBuilderResult();

    /**
     * 获取orm框架类型
     *
     * @return
     */
    DBType getDBType();

    /**
     * 获取实体类Class对象
     *
     * @return
     */
    Class<?> getEntityClass();
}
