package com.huyong.dao.helper.sqlgenerate;

import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.enums.DBType;
import com.huyong.dao.helper.utils.BuilderUtils;

import java.lang.reflect.Field;

/**
 * 描述: sql生成器
 *
 * @author huyong
 * @date 2020-03-13 9:32 下午
 */
public abstract class AbstractSqlGenerate implements Builder {
    protected Builder builder;

    public static final String SELECT_BY_PRIMARY = "selectByPrimary";

    public static final String INSERT = "insert";

    public static final String BATCH_INSERT = "batchInsert";

    public static final String COUNT = "count";

    public static final String UPDATE_BY_CONDITION = "updateByCondition";

    public static final String UPDATE_BY_PRIMARY = "updateByPrimary";

    public static final String DELETE_BY_PRIMARY = "deleteByPrimary";

    public static final String DELETE_BY_CONDITION = "deleteByCondition";

    public static final String QUERY_BY_PAGE = "queryByPage";

    public static final String QUERY_BY_CONDITION = "queryByCondition";

    public static final String QUERY_BY_CONDITION_ORDER_BY_SORT = "queryByConditionOrderBySort";
    public static final String QUERY_BY_CONDITION_ORDER_BY_SORTS = "queryByConditionOrderBySorts";
    public static final String QUERY_BY_PAGE_WITH_SORT = "queryByPageWithSort";
    public static final String QUERY_BY_PAGE_WITH_SORTS = "queryByPageWithSorts";


    /**
     * 偏移量
     */
    public static final String KEY_NAME_OFFSET = "offset";

    public static final String KEY_NAME_SIZE = "size";

    public static final String KEY_NAME_SORT = "sort";
    public static final String KEY_NAME_SORTS = "sorts";

    public Builder getBuilder() {
        return builder;
    }

    AbstractSqlGenerate(Builder builder){
        this.builder = builder;
    }

    @Override
    public StringBuilder getBuilderResult() {
        return builder.getBuilderResult();
    }

    @Override
    public DBType getDBType() {
        return builder.getDBType();
    }

    @Override
    public Class<?> getEntityClass() {
        return builder.getEntityClass();
    }

    @Override
    public void buildHeader() {
        builder.buildHeader();
    }

    @Override
    public void buildBody() {
        builder.buildBody();
        switch(getDBType()){
            case MYSQL :
                genIbatisSql(getEntityClass());
                break;
            default: break;
        }
    }

    public abstract void genIbatisSql(Class<?> clazz);

    @Override
    public void buildFooter() {
        builder.buildFooter();
    }


    protected String getTableFiledFragment(){
        return BuilderUtils.getTableFiledFragment(getEntityClass());
    }

    /**
     * 动态<where></where>属性
     *
     * @return
     */
    protected String getIbatisDynamicField(){
        StringBuilder sqlMap = new StringBuilder();
        sqlMap.append("<trim prefix=\"WHERE\"  prefixOverrides=\"AND | OR\">");
        for(Field field : getEntityClass().getDeclaredFields()){
            if(BuilderUtils.ignoreField(field)){
                continue;
            }
            sqlMap.append("<if test=\"")
                    .append(field.getName())
                    .append(" != null\" >");
            sqlMap.append("<![CDATA[ AND ")
                    .append("`")
                    .append(BuilderUtils.getUnderLineCase(field))
                    .append("`")
                    .append("=#{")
                    .append(field.getName())
                    .append("}");
            sqlMap.append("]]></if> ");
        }
        sqlMap.append("</trim>");
        return sqlMap.toString();
    }
    /**
     * 动态<where></where>属性,添加前缀
     * @return
     */
    protected String getIbatisDynamicField(String prefix){
        if (prefix == null) {
            throw new NullPointerException();
        }
        StringBuilder sqlMap = new StringBuilder();
        sqlMap.append("<trim prefix=\"WHERE\"  prefixOverrides=\"AND | OR\">");
        sqlMap.append("<if test=\"" + prefix + " != null\">");
        for(Field field : getEntityClass().getDeclaredFields()){
            if(BuilderUtils.ignoreField(field)){
                continue;
            }
            sqlMap.append("<if test=\"")
                    .append(prefix).append(".")
                    .append(field.getName())
                    .append(" != null\" >");
            sqlMap.append("<![CDATA[ AND ")
                    .append("`")
                    .append(BuilderUtils.getUnderLineCase(field))
                    .append("`")
                    .append("=#{")
                    .append(prefix).append(".")
                    .append(field.getName())
                    .append("}");
            sqlMap.append("]]></if> ");
        }
        sqlMap.append("</if>");
        sqlMap.append("</trim>");
        return sqlMap.toString();
    }

    /**
     * 动态<set></set>属性
     *
     * @return
     */
    protected String getIbatisSetDynamicField(){
        StringBuilder sqlMap = new StringBuilder();
        sqlMap.append("<trim prefix=\"SET\"  suffixOverrides=\",\">");
        for(Field field : getEntityClass().getDeclaredFields()){
            if(BuilderUtils.ignoreField(field)){
                continue;
            }
            sqlMap.append("<if test=\"").
                    append(field.getName()).
                    append("!= null\" >");
            sqlMap.append("<![CDATA[ `")
                    .append(BuilderUtils.getUnderLineCase(field))
                    .append("`=#{").append(field.getName())
                    .append("} ,");
            sqlMap.append("]]></if> ");
        }
        sqlMap.append("</trim>");
        return sqlMap.toString();
    }

    /**
     * 动态<set></set>属性,添加前缀
     *
     * @return
     */
    protected String getIbatisSetDynamicField(String prefix){
        StringBuilder sqlMap = new StringBuilder();
        sqlMap.append("<trim prefix=\"SET\"  suffixOverrides=\",\">");
        for(Field field : getEntityClass().getDeclaredFields()){
            if(BuilderUtils.ignoreField(field)){
                continue;
            }
            sqlMap.append("<if test=\"")
                    .append(prefix)
                    .append(".")
                    .append(field.getName())
                    .append("!= null\" >");
            sqlMap.append("<![CDATA[ `")
                    .append(BuilderUtils.getUnderLineCase(field))
                    .append("`=#{")
                    .append(prefix)
                    .append(".")
                    .append(field.getName())
                    .append("} ,");
            sqlMap.append("]]></if> ");
        }
        sqlMap.append("</trim>");
        return sqlMap.toString();
    }



    protected String getIbatisDynamicSorts(){
        StringBuilder sqlMap = new StringBuilder();
        sqlMap.append("<if test=\"" + AbstractSqlGenerate.KEY_NAME_SORTS +" != null\">");
        sqlMap.append("order by ");
        sqlMap.append("<foreach open=\"\" close=\"\" separator=\",\" item=\"item\" index=\"index\" collection=\""+ AbstractSqlGenerate.KEY_NAME_SORTS +"\">");
        sqlMap.append("`${item.sortField}`  ${item.sortType}");
        sqlMap.append("</foreach></if>");
        return sqlMap.toString();
    }

    protected String getIbatisDynamicSort(){
        StringBuilder sqlMap = new StringBuilder();
        sqlMap.append("<if test=\"" + AbstractSqlGenerate.KEY_NAME_SORT +" != null\">");
        sqlMap.append("order by ");
        sqlMap.append("`${sort.sortField}`  ${sort.sortType}");
        sqlMap.append("</if>");
        return sqlMap.toString();
    }
}
