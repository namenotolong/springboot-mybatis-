package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.utils.BuilderUtils;

/**
 * 描述: query page sql generate
 *
 * @author huyong
 * @date 2020-03-13 10:04 下午
 */
public class QueryByPageGenerate extends AbstractSqlGenerate {
    public QueryByPageGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<select id =\"")
                .append(AbstractSqlGenerate.QUERY_BY_PAGE)
                .append("\" resultMap =\"")
                .append(BuilderUtils.getResultMapName(getEntityClass()))
                .append("\">");
        getBuilderResult()
                .append("<include refid=\"")
                .append(BuilderUtils.getSelectFragment(clazz))
                .append("\" /> ");
        getBuilderResult().append(getIbatisDynamicField("condition"));
        getBuilderResult().append(" limit #{" + AbstractSqlGenerate.KEY_NAME_OFFSET + "},#{" + AbstractSqlGenerate.KEY_NAME_SIZE + "}</select>");
    }
}
