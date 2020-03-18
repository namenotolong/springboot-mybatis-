package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.utils.BuilderUtils;

/**
 * 描述: update by primary generate
 *
 * @author huyong
 * @date 2020-03-13 10:14 下午
 */
public class UpdateByConditionGenerate extends AbstractSqlGenerate {

    public UpdateByConditionGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<update id =\"" + AbstractSqlGenerate.UPDATE_BY_CONDITION +"\"" + " parameterType=\"" + clazz.getName() +"\">");
        getBuilderResult().append("update ")
                .append(BuilderUtils.getTableName(clazz))
                .append(getIbatisSetDynamicField("target"))
                .append(getIbatisDynamicField("condition"))
                .append("</update>");
    }

}