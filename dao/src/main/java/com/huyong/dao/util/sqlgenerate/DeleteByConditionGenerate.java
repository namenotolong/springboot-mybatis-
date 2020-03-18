package com.huyong.dao.util.sqlgenerate;

import com.huyong.dao.util.Builder;
import com.huyong.dao.util.utils.BuilderUtils;

/**
 * 描述: delete sql generate
 *
 * @author huyong
 * @date 2020-03-13 9:52 下午
 */
public class DeleteByConditionGenerate extends AbstractSqlGenerate {
    public DeleteByConditionGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<delete id =\"").append(AbstractSqlGenerate.DELETE_BY_CONDITION).append("\">");
        getBuilderResult()
                .append("delete from ")
                .append(BuilderUtils.getTableName(clazz))
                .append(getIbatisDynamicField())
                .append("</delete>");
    }
}
