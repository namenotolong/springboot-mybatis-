package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.utils.BuilderUtils;

import java.lang.reflect.Field;

/**
 * 描述: 批量插入sql生成
 *
 * @author huyong
 * @date 2020-03-13 9:43 下午
 */
public class BatchInsertGenerate extends AbstractSqlGenerate {
    public BatchInsertGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {

        StringBuilder sql = new StringBuilder();

        sql.append("<insert id =\"").append(AbstractSqlGenerate.BATCH_INSERT).append("\"").append(" useGeneratedKeys=\"true\" keyProperty=\"id\" parameterType=\"list\" >");
        sql.append("insert into ").append(BuilderUtils.getTableName(clazz)).append("(").append(getTableFiledFragment()).append(")" +
                " values <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">  (");
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (BuilderUtils.ignoreField(field)) {
                continue;
            }
            sql.append("#{item.").append(field.getName()).append("}");
            sql.append(Builder.SEP_COMMA);
        }

        if(sql.toString().endsWith(SEP_COMMA)){
            sql = new StringBuilder(sql.substring(0, sql.length()-1));
        }

        getBuilderResult().append(sql);

        getBuilderResult().append(")</foreach></insert>");
    }
}
