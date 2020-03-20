package com.huyong.utils;

import com.huyong.constant.CommonConstant;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 描述: StringUtils
 *
 * @author huyong
 * @date 2020-03-20 2:45 下午
 */
public class DataUtils {
    /**
     * 获取四位随机数
     * @return
     */
    public static String getRandom() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CommonConstant.CODE_LENGTH; i++) {
            sb.append(current.nextInt(10));
        }
        return sb.toString();
    }
}
