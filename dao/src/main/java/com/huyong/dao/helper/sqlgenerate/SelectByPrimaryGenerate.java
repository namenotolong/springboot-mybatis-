package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.utils.BuilderUtils;

/**
 * 描述: select by primary generate
 *
 * @author huyong
 * @date 2020-03-13 10:14 下午
 */
public class SelectByPrimaryGenerate extends AbstractSqlGenerate {

    public SelectByPrimaryGenerate(Builder builder) {
        super(builder);
    }

    @Override
    public void genIbatisSql(Class<?> clazz){
        getBuilderResult().append("<select id =\"" + BuilderUtils.getStatement(clazz) + "." + AbstractSqlGenerate.SELECT_BY_PRIMARY +"\" resultMap =\"" + BuilderUtils.getResultMapName(getEntityClass()) + "\" parameterType=\"long\" >");
        getBuilderResult().append("<include refid=\""+ BuilderUtils.getSelectFragment(clazz) + "\" /> ");
        getBuilderResult().append("<![CDATA[where id = #{id} ]]></select>");
    }

}
