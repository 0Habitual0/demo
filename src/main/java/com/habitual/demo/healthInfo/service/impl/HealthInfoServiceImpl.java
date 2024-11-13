package com.habitual.demo.healthInfo.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.controller.HealthInfoController;
import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoPageDto;
import com.habitual.demo.healthInfo.repository.HealthInfoRepository;
import com.habitual.demo.healthInfo.service.HealthInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 健康资讯
 */
@Service
public class HealthInfoServiceImpl implements HealthInfoService {

    @Autowired
    private HealthInfoRepository healthInfoRepository;

    @Override
    public CommonResponse save(HealthInfoEntity healthInfoEntity) {
        try {
            HealthInfoEntity savedEntity = healthInfoRepository.save(healthInfoEntity);
            return CommonResponse.success(savedEntity);
        } catch (Exception e) {
            return CommonResponse.fail("保存失败: " + e.getMessage());
        }
    }

    @Override
    public CommonResponse delete(Long id) {
        try {
            if (healthInfoRepository.existsById(id)) {
                healthInfoRepository.deleteById(id);
                return CommonResponse.success("删除成功");
            } else {
                return CommonResponse.fail("删除失败：未找到对应的记录");
            }
        } catch (Exception e) {
            return CommonResponse.fail("删除失败：" + e.getMessage());
        }
    }

    @Override
    public CommonResponse selectByPage(HealthInfoPageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum()-1, input.getPageSize());
        Page<HealthInfoEntity> result = healthInfoRepository.findByTitleAndType(input.getTitle(), input.getType(), pageable);
        return CommonResponse.success(result);
    }

}
