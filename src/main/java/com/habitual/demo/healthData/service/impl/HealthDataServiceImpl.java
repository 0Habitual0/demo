package com.habitual.demo.healthData.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.security.UserContext;
import com.habitual.demo.healthData.entity.HealthDataEntity;
import com.habitual.demo.healthData.entity.dto.HealthDataPageDto;
import com.habitual.demo.healthData.repository.HealthDataRepository;
import com.habitual.demo.healthData.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
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
        if (input.getUserId() == null) {
            input.setUserId(UserContext.getId());
        }
        healthDataRepository.save(input);
        return CommonResponse.success("保存成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        healthDataRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(HealthDataPageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum()-1, input.getPageSize());
        Page<HealthDataEntity> result = healthDataRepository.findByCriteria(
                input.getUserId(),
                input.getCreateAfter(),
                input.getCreateBefore(),
                input.getRemark(),
                pageable);
        return CommonResponse.success(new PagedModel<>(result));
    }

}
