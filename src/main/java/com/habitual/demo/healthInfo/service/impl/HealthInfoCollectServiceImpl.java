package com.habitual.demo.healthInfo.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;
import com.habitual.demo.healthInfo.repository.HealthInfoCollectRepository;
import com.habitual.demo.healthInfo.service.HealthInfoCollectService;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 健康资讯收藏
 */
@Service
public class HealthInfoCollectServiceImpl implements HealthInfoCollectService {

    @Autowired
    private HealthInfoCollectRepository healthInfoCollectRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public CommonResponse collect(HealthInfoCollectEntity input) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            UserEntity user = userRepository.findByUsername(authentication.getName());
            if (user != null) {
                HealthInfoCollectEntity healthInfoCollectEntity = healthInfoCollectRepository.findByUserIdAndHealthInfoId(user.getId(), input.getHealthInfoId());
                if (healthInfoCollectEntity != null) {
                    healthInfoCollectRepository.delete(healthInfoCollectEntity);
                } else {
                    input.setUserId(user.getId());
                    healthInfoCollectRepository.save(input);
                }
                return CommonResponse.success("成功");
            }
        }
        return CommonResponse.fail("收藏失败");
    }

}
