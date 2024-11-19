package com.habitual.demo.healthInfo.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;
import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoPageDto;

import java.util.List;

/**
 * 业务层 健康资讯
 */
public interface HealthInfoService {

    CommonResponse save(HealthInfoEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(HealthInfoPageDto input);

    CommonResponse selectByPageCollect(HealthInfoPageDto input);

    CommonResponse selectLatest();

}
