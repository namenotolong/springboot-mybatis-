package com.huyong.dao.util.sqlgenerate;

import com.huyong.dao.util.Builder;
import com.huyong.dao.util.utils.BuilderUtils;

/**
 * 描述: count sql 生成器
 *
 * @author huyong
 * @date 2020-03-13 9:46 下午
 */
public class CountGenerate extends AbstractSqlGenerate {

    public CountGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<select id =\"").append(AbstractSqlGenerate.COUNT).append("\" resultType =\"int\" parameterType=\"java.util.Map\" >");
        getBuilderResult().append("select count(1) from ").append(BuilderUtils.getTableName(clazz)).append("\n");
        getBuilderResult().append(getIbatisDynamicField());
        getBuilderResult().append("</select>");
    }

}
