package com.habitual.demo.healthInfo.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.security.UserContext;
import com.habitual.demo.healthInfo.entity.HealthInfoCommentEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoCommentPageDto;
import com.habitual.demo.healthInfo.repository.HealthInfoCommentRepository;
import com.habitual.demo.healthInfo.service.HealthInfoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
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
        if (UserContext.getId() != null) {
            input.setUserId(UserContext.getId());
            healthInfoCommentRepository.save(input);
            return CommonResponse.success("保存成功");
        }

        return CommonResponse.fail("保存失败");
    }

    @Override
    public CommonResponse delete(Long id) {
        healthInfoCommentRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(HealthInfoCommentPageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum()-1, input.getPageSize());
        Page<HealthInfoCommentEntity> result = healthInfoCommentRepository.findByCriteria(
                input.getContent(),
                input.getCreateBy(),
                input.getUpdateBy(),
                input.getRemark(),
                pageable);
        return CommonResponse.success(new PagedModel<>(result));
    }

    @Override
    public CommonResponse selectByHealthInfo(Long healthInfoId) {
        return CommonResponse.success(healthInfoCommentRepository.findByHealthInfoIdOrderByCreateTimeDesc(healthInfoId));
    }

}
