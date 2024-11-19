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
            } else {
                input.setUserId(UserContext.getId());
                healthInfoCollectRepository.save(input);
            }
            return CommonResponse.success("成功");
        }

        return CommonResponse.fail("收藏失败");
    }

}
