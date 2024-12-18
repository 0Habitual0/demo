package com.habitual.demo.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 线程局部变量类使用 用户信息
 */
@Getter
@Setter
public class UserInfo {

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 用户名
     */
    private String nickName;

}
