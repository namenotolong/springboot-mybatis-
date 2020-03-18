package com.huyong.dao.util.sqlgenerate;

import com.huyong.dao.util.Builder;
import com.huyong.dao.util.utils.BuilderUtils;

/**
 * 描述: insert sql generate
 *
 * @author huyong
 * @date 2020-03-13 9:53 下午
 */
public class InsertGenerate extends AbstractSqlGenerate {
    public InsertGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz){

        StringBuilder sql = new StringBuilder();
        sql.append("<insert id =\"")
                .append(AbstractSqlGenerate.INSERT)
                .append("\"")
                .append(" useGeneratedKeys=\"true\" keyProperty=\"id\" parameterType=\"")
                .append(clazz.getName())
                .append("\" >");
        sql.append("insert into ")
                .append(BuilderUtils.getTableName(clazz))
                .append("(<include refid=\"")
                .append(BuilderUtils.getInsertColumnsFragment(getEntityClass()))
                .append("\"/>)values(")
                .append("<include refid=\"")
                .append(BuilderUtils.getInsertValuesFragment(getEntityClass()))
                .append("\"/>)")
                .append("</insert>");
        getBuilderResult().append(sql);
    }

}
