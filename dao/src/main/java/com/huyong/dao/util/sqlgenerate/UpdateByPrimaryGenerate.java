package com.huyong.dao.util.sqlgenerate;

import com.huyong.dao.util.Builder;
import com.huyong.dao.util.utils.BuilderUtils;

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
        getBuilderResult()
                .append("<update id =\"" + AbstractSqlGenerate.UPDATE_BY_PRIMARY + "\"" + " parameterType=\"")
                .append(clazz.getName())
                .append("\">");
        getBuilderResult().append("update ").
                append(BuilderUtils.getTableName(clazz))
                .append(getIbatisSetDynamicField())
                .append(" WHERE ID = #{id} </update>");
    }

}