package com.huyong.dao.helper.utils;

import com.huyong.constant.CommonConstant;
import com.huyong.dao.helper.Builder;
import com.huyong.dao.helper.annotation.Ignore;
import com.huyong.dao.helper.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 描述: 组装工具集
 *
 * @author huyong
 * @date 2020-03-13 8:42 下午
 */
public class BuilderUtils {
    /**
     * ibatis statement
     *
     * @param clazz
     * @return
     */
    public static String getStatement(Class<?> clazz) {
        return CommonConstant.MAPPER_NAME_SPACE + clazz.getSimpleName();
    }

    /**
     * 变量名转成下划线模式
     *
     * @param f
     * @return
     */
    public static String getUnderLineCase(Field f){
        return TransformUtils.camelToUnderline(f.getName());
    }

    /**
     * resultMap名称
     *
     * @param clazz
     * @return
     */
    public static String getResultMapName(Class<?> clazz){
        return getClassAliasName(clazz) + "Result";
    }

    /**
     * 实体类别名
     *
     * @return
     */
    public static String getClassAliasName(Class<?> clazz){
        return TransformUtils.initialLower(clazz.getSimpleName());
    }

    /**
     * 获取注解表名，如果未注解未设置，则默认返回tsp_#实体类名称下划线规则
     * commentDTO > tsp_comment_dto
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class<?> clazz){
        String tableName;
        if (clazz.isAnnotationPresent(Table.class)) {
            tableName = clazz.getAnnotation(Table.class).tableName();
        }else{
            tableName = TransformUtils.camelToUnderline(clazz.getSimpleName());
        }
        return tableName;
    }


    /**
     * 获取字段列表片段
     *
     * @param clazz
     * @return
     */
    public static String getTableFiledFragment(Class<?> clazz){
        StringBuilder fragment = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (ignoreField(field)) {
                continue;
            }
            fragment.append("`")
                    .append(BuilderUtils.getUnderLineCase(field))
                    .append("`")
                    .append(Builder.SEP_COMMA);
        }

        if(fragment.toString().endsWith(Builder.SEP_COMMA)){
            fragment = new StringBuilder(fragment.substring(0, fragment.length() - 1));
        }
        return fragment.toString();
    }

    public static boolean ignoreField(Field f){
        return Modifier.isStatic(f.getModifiers()) || f.getAnnotation(Ignore.class) != null;
    }

    public static String getSelectFragment(Class<?> clazz){
        return clazz.getSimpleName() + "_select_fragment";
    }

}
