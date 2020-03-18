package com.huyong.dao.helper.dbbuilder;


import com.huyong.dao.helper.Builder;

/**
 * 描述: mapper抽象组装器，提供一个构建模板
 *
 * @author huyong
 * @date 2020-03-13 8:37 下午
 */
public abstract class AbstractBuilder implements Builder {

    /**
     * 组装内容
     */
    public StringBuilder sqlMap = new StringBuilder();

    protected Class<?> clazz;
    protected Class<?> mapper;

    AbstractBuilder(Class<?> clazz){
        this.clazz = clazz;
    }

    AbstractBuilder(Class<?> clazz, Class<?> mapper){
        this.clazz = clazz;
        this.mapper = mapper;
    }

    @Override
    public Class<?> getEntityClass() {
        return this.clazz;
    }

    @Override
    public StringBuilder getBuilderResult(){
        return sqlMap;
    }
}
