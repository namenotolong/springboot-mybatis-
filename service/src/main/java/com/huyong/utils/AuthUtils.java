package com.huyong.utils;

import com.huyong.dao.entity.UserDO;


/**
 * 描述: 用户工具类
 *
 * @author huyong
 * @date 2020-02-20 3:49 下午
 */
public class AuthUtils {
    private static ThreadLocal<UserDO> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<Boolean> ignore = new ThreadLocal<>();
    public static void setUser(UserDO userDO) {
        threadLocal.set(userDO);
        ignore.set(false);
    }
    public static UserDO getUser() {
        return threadLocal.get();
    }
    public static void clear() {
        threadLocal.remove();;
        ignore.remove();
    }
    public static void setIgnore(boolean flag) {
        ignore.set(flag);
    }
    public static boolean getIgnore() {
        return ignore.get();
    }
}
