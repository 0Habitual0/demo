package com.habitual.demo.user.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserPageDto;
import com.habitual.demo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity.getUsername(), userEntity.getPassword());
    }

    /**
     * 获取用户信息
     */
    @GetMapping("info")
    public CommonResponse info() {
        return userService.info();
    }

    /**
     * 登出
     */
    @PostMapping("logout")
    public CommonResponse logout() {
        return userService.logout();
    }

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody UserEntity input) {
        return userService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return userService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody UserPageDto input) {
        return userService.selectByPage(input);
    }

}
