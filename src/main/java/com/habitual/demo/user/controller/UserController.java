package com.habitual.demo.user.controller;

import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("login")
    public void login(@RequestBody UserEntity userEntity) {

    }

    /**
     * 登出
     */
    @PostMapping("logout")
    public void logout(@RequestBody UserEntity userEntity) {

    }

}
