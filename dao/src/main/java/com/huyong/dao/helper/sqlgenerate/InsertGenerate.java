package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.sqlgenerate.AbstractSqlGenerate;
import com.huyong.dao.helper.utils.BuilderUtils;

import java.lang.reflect.Field;

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

        sql.append("<insert id =\"").append(BuilderUtils.getStatement(clazz)).append(".").append(AbstractSqlGenerate.INSERT).append("\"").append(" useGeneratedKeys=\"true\" keyProperty=\"id\" parameterType=\"").append(clazz.getName()).append("\" >");
        sql.append("insert into ").append(BuilderUtils.getTableName(clazz)).append("(").append(getTableFiledFragment()).append(")values(");
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (BuilderUtils.ignoreField(field)) {
                continue;
            }
            sql.append("#{").append(field.getName()).append("}");
            sql.append(Builder.SEP_COMMA);
        }
        if(sql.toString().endsWith(SEP_COMMA)){
            sql = new StringBuilder(sql.substring(0, sql.length()-1));
        }

        getBuilderResult().append(sql);

        getBuilderResult().append(")</insert>");
    }

}
