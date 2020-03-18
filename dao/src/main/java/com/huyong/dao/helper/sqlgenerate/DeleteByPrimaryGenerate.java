package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.utils.BuilderUtils;

/**
 * 描述: delete sql generate
 *
 * @author huyong
 * @date 2020-03-13 9:52 下午
 */
public class DeleteByPrimaryGenerate extends AbstractSqlGenerate {
    public DeleteByPrimaryGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz) {
        getBuilderResult().append("<delete id =\"").append(AbstractSqlGenerate.DELETE_BY_PRIMARY).append("\" parameterType=\"long\" >");
        getBuilderResult().append("<![CDATA[ delete from ").append(BuilderUtils.getTableName(clazz)).append(" where id = #{id} ]]></delete>");
    }
}
