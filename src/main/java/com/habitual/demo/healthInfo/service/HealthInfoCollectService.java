package com.habitual.demo.healthInfo.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;

/**
 * 业务层 健康资讯收藏
 */
public interface HealthInfoCollectService {

    CommonResponse collect(HealthInfoCollectEntity input);

    CommonResponse isCollect(Long healthInfoId);

}
