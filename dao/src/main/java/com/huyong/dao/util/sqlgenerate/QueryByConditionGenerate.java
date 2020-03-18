package com.huyong.dao.util.sqlgenerate;

import com.huyong.dao.util.Builder;
import com.huyong.dao.util.utils.BuilderUtils;

/**
 * 描述: queryByCondition sql generate
 *
 * @author huyong
 * @date 2020-03-13 9:54 下午
 */
public class QueryByConditionGenerate extends AbstractSqlGenerate {
    public QueryByConditionGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<select id =\"").append(AbstractSqlGenerate.QUERY_BY_CONDITION).append("\" resultMap =\"").append(BuilderUtils.getResultMapName(getEntityClass())).append("\" parameterType=\"java.util.Map\" >");
        getBuilderResult().append("<include refid=\"").append(BuilderUtils.getSelectFragment(clazz)).append("\" /> ");
        getBuilderResult().append(getIbatisDynamicField());
        getBuilderResult().append("</select>");
    }
}
