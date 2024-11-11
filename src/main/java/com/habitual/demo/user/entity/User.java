package com.habitual.demo.user.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 用户
 */
@Getter
@Setter
@Entity
public class User extends BaseEntity {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号
     */
    private String tel;

    /**
     * 角色
     */
    private String role;

}
