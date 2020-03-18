package com.huyong.dao.util.sqlgenerate;

import com.huyong.dao.util.Builder;
import com.huyong.dao.util.utils.BuilderUtils;

/**
 * 描述: QueryByConditionOrderBySortGenerate sql generate
 *
 * @author huyong
 * @date 2020-03-13 9:54 下午
 */
public class QueryByConditionOrderBySortGenerate extends AbstractSqlGenerate {
    public QueryByConditionOrderBySortGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<select id =\"").append(AbstractSqlGenerate.QUERY_BY_CONDITION_ORDER_BY_SORT).append("\" resultMap =\"").append(BuilderUtils.getResultMapName(getEntityClass())).append("\">");
        getBuilderResult().append("<include refid=\"").append(BuilderUtils.getSelectFragment(clazz)).append("\" /> ");
        getBuilderResult().append(getIbatisDynamicField("condition"));
        getBuilderResult().append(getIbatisDynamicSort());
        getBuilderResult().append("</select>");
    }
}
