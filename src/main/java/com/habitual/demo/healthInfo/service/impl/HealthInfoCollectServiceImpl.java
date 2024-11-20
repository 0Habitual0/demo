package com.habitual.demo.healthInfo.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.security.UserContext;
import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;
import com.habitual.demo.healthInfo.repository.HealthInfoCollectRepository;
import com.habitual.demo.healthInfo.service.HealthInfoCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 健康资讯收藏
 */
@Service
public class HealthInfoCollectServiceImpl implements HealthInfoCollectService {

    @Autowired
    private HealthInfoCollectRepository healthInfoCollectRepository;

    @Override
    public CommonResponse collect(HealthInfoCollectEntity input) {
        if (UserContext.getId() != null) {
            HealthInfoCollectEntity healthInfoCollectEntity = healthInfoCollectRepository.findByUserIdAndHealthInfoId(UserContext.getId(), input.getHealthInfoId());
            if (healthInfoCollectEntity != null) {
                healthInfoCollectRepository.delete(healthInfoCollectEntity);
                return CommonResponse.success(false);
            } else {
                input.setUserId(UserContext.getId());
                healthInfoCollectRepository.save(input);
                return CommonResponse.success(true);
            }
        }

        return CommonResponse.fail("收藏失败");
    }

    @Override
    public CommonResponse isCollect(Long healthInfoId) {
        if (UserContext.getId() != null) {
            HealthInfoCollectEntity healthInfoCollectEntity = healthInfoCollectRepository.findByUserIdAndHealthInfoId(UserContext.getId(), healthInfoId);
            if (healthInfoCollectEntity != null) {
                return CommonResponse.success(true);
            } else {
                return CommonResponse.success(false);
            }
        }
        return CommonResponse.fail("查询用户信息失败");
    }

}
