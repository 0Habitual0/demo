package com.habitual.demo.common.security;

import com.habitual.demo.user.entity.UserEntity;

/**
 * 线程局部变量类 获取用户信息 TODO 暂未设置
 */
public class UserContext {

    private static final ThreadLocal<UserEntity> userHolder = new ThreadLocal<>();

    public static void setUser(UserEntity user) {
        userHolder.set(user);
    }

    public static UserEntity getUser() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }

}
