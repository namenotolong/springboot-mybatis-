package com.huyong.dao.helper.dbbuilder;

import com.huyong.dao.helper.enums.DBType;
import com.huyong.dao.helper.utils.BuilderUtils;

import java.lang.reflect.Field;

/**
 * 描述: mysql mapper 构建
 *
 * @author huyong
 * @date 2020-03-13 8:36 下午
 */
public class MysqlBuilder extends AbstractBuilder {

    public MysqlBuilder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void buildHeader() {
        sqlMap.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        sqlMap.append("<mapper namespace=\"" + BuilderUtils.getStatement(clazz) + "\"> \n");
        genResultMap();
        genSelectFragment();
    }

    private void genResultMap(){
        sqlMap.append("<resultMap id=\"" + BuilderUtils.getResultMapName(clazz) + "\" type=\"" + clazz.getName() + "\">");
        for(Field f : clazz.getDeclaredFields()){
            if(BuilderUtils.ignoreField(f)){
                continue;
            }
            sqlMap.append("<result property=\"").append(f.getName()).append("\" column=\"").append(BuilderUtils.getUnderLineCase(f)).append("\" />");
        }
        sqlMap.append("</resultMap> \n");
    }

    private void genSelectFragment(){
        sqlMap.append("<sql id=\"").append(BuilderUtils.getSelectFragment(clazz)).append("\">");
        sqlMap.append("select ");
        sqlMap.append(BuilderUtils.getTableFiledFragment(clazz));
        sqlMap.append(" from ")
                .append("`")
                .append(BuilderUtils.getTableName(clazz))
                .append("`")
                .append("</sql> \n");
    }


    @Override
    public void buildBody() {
    }

    @Override
    public void buildFooter() {
        sqlMap.append("</mapper>");
    }
    @Override
    public DBType getDBType() {
        return DBType.MYSQL;
    }
}
