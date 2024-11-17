package com.habitual.demo.user.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.utils.JwtTokenUtil;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserChangePasswordDto;
import com.habitual.demo.user.entity.dto.UserInfoDto;
import com.habitual.demo.user.entity.dto.UserPageDto;
import com.habitual.demo.user.repository.UserRepository;
import com.habitual.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 业务层实现 用户
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public CommonResponse register(UserEntity input) {
        // 检查登录账号是否重复
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser != null) {
            return CommonResponse.fail("登录账号已存在");
        }
        input.setId(null);
        userRepository.save(input);
        return CommonResponse.success("注册成功");
    }

    @Override
    public CommonResponse retrievePassword(UserEntity input) {
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser == null) {
            return CommonResponse.fail("账号不存在");
        }
        if (Objects.equals(existingUser.getEmail(), input.getEmail())) {
            existingUser.setPassword(input.getPassword());
            userRepository.save(existingUser);
            return CommonResponse.success("修改成功");
        } else {
            return CommonResponse.fail("邮箱不正确");
        }
    }

    @Override
    public CommonResponse changePassword(UserChangePasswordDto input) {
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser == null) {
            return CommonResponse.fail("账号不存在");
        }
        if (Objects.equals(existingUser.getPassword(), input.getOldPassword())) {
            existingUser.setPassword(input.getNewPassword());
            userRepository.save(existingUser);
            return CommonResponse.success("修改成功");
        } else {
            return CommonResponse.fail("旧密码不正确");
        }
    }

    @Override
    public CommonResponse login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsernameAndPassword(username, password);
        if (userEntity != null && Objects.equals(userEntity.getStatus(), 1L)) {
            return CommonResponse.success(jwtTokenUtil.getToken(username));
        } else if (userEntity == null) {
            return CommonResponse.fail("用户名或密码错误");
        } else {
            return CommonResponse.fail("账户已被禁用，请联系管理员");
        }
    }

    @Override
    public CommonResponse info() {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getName() != null) {
            username = authentication.getName();
        } else {
            return CommonResponse.fail("用户未认证或用户名为空");
        }
        UserInfoDto info = new UserInfoDto();
        info.setUsername(username);
        info.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return CommonResponse.success(info);
    }

    @Override
    public CommonResponse logout() {
        jwtTokenUtil.deleteToken();
        return CommonResponse.success(null);
    }

    @Override
    public CommonResponse save(UserEntity input) {
        // 检查登录账号是否重复
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser != null && !Objects.equals(existingUser.getId(), input.getId())) {
            return CommonResponse.fail("登录账号已存在");
        }
        if (input.getId() != null) {
            // 更新操作时不插入密码
            userRepository.findById(input.getId()).ifPresent(userToUpdate -> input.setPassword(userToUpdate.getPassword()));
        }
        userRepository.save(input);
        return CommonResponse.success("保存成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        userRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(UserPageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum() - 1, input.getPageSize());
        Page<UserEntity> result = userRepository.findByCriteria(
                input.getUsername(),
                input.getNickName(),
                input.getSex(),
                input.getAge(),
                input.getEmail(),
                input.getTel(),
                input.getRole(),
                input.getStatus(),
                pageable);
        result.forEach(user -> user.setPassword(null));
        return CommonResponse.success(new PagedModel<>(result));
    }

}
