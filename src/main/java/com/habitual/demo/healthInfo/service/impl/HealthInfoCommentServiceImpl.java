package com.habitual.demo.healthInfo.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCommentEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoCommentPageDto;
import com.habitual.demo.healthInfo.repository.HealthInfoCommentRepository;
import com.habitual.demo.healthInfo.service.HealthInfoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 健康资讯评论
 */
@Service
public class HealthInfoCommentServiceImpl implements HealthInfoCommentService {

    @Autowired
    private HealthInfoCommentRepository healthInfoCommentRepository;

    @Override
    public CommonResponse save(HealthInfoCommentEntity input) {
        return null;
    }

    @Override
    public CommonResponse delete(Long id) {
        return null;
    }

    @Override
    public CommonResponse selectByPage(HealthInfoCommentPageDto input) {
        return null;
    }

    @Override
    public CommonResponse selectByHealthInfo(Long healthInfoId) {
        return null;
    }

}
