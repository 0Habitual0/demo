package com.habitual.demo.user.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.utils.JwtTokenUtil;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserPageDto;
import com.habitual.demo.user.repository.UserRepository;
import com.habitual.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public CommonResponse login(String username, String password) {
        return CommonResponse.success(jwtTokenUtil.generateToken(username)); //TODO
    }

    @Override
    public void logout() {
        jwtTokenUtil.deleteToken();
    }

    @Override
    public CommonResponse save(UserEntity input) {
        // 检查用户名是否重复
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());

        if (existingUser != null && !existingUser.getId().equals(input.getId())) {
            return CommonResponse.fail("用户名已存在");
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
        Pageable pageable = PageRequest.of(input.getPageNum()-1, input.getPageSize());
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
        return CommonResponse.success(result);
    }

}
