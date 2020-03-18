package com.huyong.utils;


/**
 * 描述: 用户工具类
 *
 * @author huyong
 * @date 2020-02-20 3:49 下午
 */
public class AuthUtils {
    private static ThreadLocal<UserBO> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<Boolean> ignore = new ThreadLocal<>();
    public static void setUser(UserBO user) {
        threadLocal.set(user);
        ignore.set(false);
    }
    public static UserBO getUser() {
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
