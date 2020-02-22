package com.huyong.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * @author: huyong
 **/
public class GsonUtils {


    private static final Gson gson = new Gson();


    /**
     * 对象序列化json串
     * @param target
     * @return
     */
    public static  String toJson(Object target){

        return gson.toJson(target);
    }

    /**
     * 反序列化
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T fromJson(String json,Class<T> clazz){
        if(StringUtils.isEmpty(json)){
            return null;
        }
        return gson.fromJson(json,clazz);
    }


    /**
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type  type){
        if(StringUtils.isEmpty(json)){
            return null;
        }
        return gson.fromJson(json,type);
    }

    /**
     *
     * @param json
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, T t){
        Type type = new TypeToken<T>(){}.getType();

        return fromJson(json,type);
    }


}

    
