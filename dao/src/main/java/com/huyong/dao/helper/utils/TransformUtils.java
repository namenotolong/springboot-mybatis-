package com.huyong.dao.helper.utils;

import com.google.common.base.CaseFormat;

/**
 * 描述: 转换工具集
 *
 * @author huyong
 * @date 2020-03-13 8:43 下午
 */
public class TransformUtils {
    /**
     * 驼峰转下划线
     * @return
     */
    public static String camelToUnderline(String s) {
        if (null == s || "".equals(s.trim())) {
            return s;
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }
    /**
     * 下划线转驼峰
     * @return
     */
    public static String underLineToCamel(String name) {
        if (null == name || "".equals(name.trim())) {
            return name;
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }


    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    public static String initialLower(String s){
        char[] cArray = s.toCharArray();
        cArray[0] = Character.toLowerCase(cArray[0]);
        return String.valueOf(cArray);
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String initialUpper(String s){
        char[] cArray = s.toCharArray();
        cArray[0] = Character.toUpperCase(cArray[0]);
        return String.valueOf(cArray);
    }
}
