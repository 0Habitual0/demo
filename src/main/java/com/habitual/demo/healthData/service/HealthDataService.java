package com.habitual.demo.healthData.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthData.entity.HealthDataEntity;
import com.habitual.demo.healthData.entity.dto.HealthDataPageDto;

/**
 * 业务层 健康数据
 */
public interface HealthDataService {

    CommonResponse save(HealthDataEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(HealthDataPageDto input);

    CommonResponse trendChart();

    CommonResponse barChart();

}
