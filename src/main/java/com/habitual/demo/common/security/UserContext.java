package com.habitual.demo.common.security;

import com.habitual.demo.common.entity.UserInfo;

/**
 * 线程局部变量类 获取用户信息
 */
public class UserContext {

    private static final ThreadLocal<UserInfo> userHolder = new ThreadLocal<>();

    public static void setUser(UserInfo user) {
        userHolder.set(user);
    }

    public static Long getId() {
        return userHolder.get().getId();
    }

    public static String getUsername() {
        return userHolder.get().getUsername();
    }

    public static String getNickName() {
        return userHolder.get().getNickName();
    }

    public static void clear() {
        userHolder.remove();
    }

}
