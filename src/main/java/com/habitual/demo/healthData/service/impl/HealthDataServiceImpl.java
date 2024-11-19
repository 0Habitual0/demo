package com.habitual.demo.healthData.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthData.entity.HealthDataEntity;
import com.habitual.demo.healthData.repository.HealthDataRepository;
import com.habitual.demo.healthData.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 健康数据
 */
@Service
public class HealthDataServiceImpl implements HealthDataService {

    @Autowired
    private HealthDataRepository healthDataRepository;

    @Override
    public CommonResponse save(HealthDataEntity input) {
        healthDataRepository.save(input);
        return CommonResponse.success("保存成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        healthDataRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

}
