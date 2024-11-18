package com.habitual.demo.healthInfo.service.impl;

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


}
