package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.utils.BuilderUtils;

import java.lang.reflect.Field;

/**
 * 描述: update by primary generate
 *
 * @author huyong
 * @date 2020-03-13 10:14 下午
 */
public class UpdateByPrimaryGenerate extends AbstractSqlGenerate {

    public UpdateByPrimaryGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<update id =\"" + BuilderUtils.getStatement(clazz) + "." + AbstractSqlGenerate.UPDATE_BY_PRIMARY +"\"" + " parameterType=\"" + clazz.getName() +"\">");
        getBuilderResult().append("update " + BuilderUtils.getTableName(clazz) + " <trim prefix=\"SET\" suffixOverrides=\",\">") ;
        for(Field field : clazz.getDeclaredFields()){
            if(BuilderUtils.ignoreField(field)){
                continue;
            }
            getBuilderResult().append("<if test=\"" + field.getName() +"!= null\" >");
            getBuilderResult().append("<![CDATA[ " + BuilderUtils.getUnderLineCase(field) + "=#{" + field.getName() + "} ,");
            getBuilderResult().append("]]></if> ");
        }
        getBuilderResult().append("</trim> WHERE ID = #{id} </update>");
    }

}