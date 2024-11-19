package com.habitual.demo.healthInfo.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCommentEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoCommentPageDto;

/**
 * 业务层 健康资讯评论
 */
public interface HealthInfoCommentService {

    CommonResponse save(HealthInfoCommentEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(HealthInfoCommentPageDto input);

    CommonResponse selectByHealthInfo(Long healthInfoId);

}
