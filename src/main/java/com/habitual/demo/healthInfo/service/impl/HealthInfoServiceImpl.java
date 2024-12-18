package com.habitual.demo.healthInfo.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.security.UserContext;
import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;
import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoPageDto;
import com.habitual.demo.healthInfo.repository.HealthInfoCollectRepository;
import com.habitual.demo.healthInfo.repository.HealthInfoRepository;
import com.habitual.demo.healthInfo.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务层实现 健康资讯
 */
@Service
public class HealthInfoServiceImpl implements HealthInfoService {

    @Autowired
    private HealthInfoRepository healthInfoRepository;

    @Autowired
    private HealthInfoCollectRepository healthInfoCollectRepository;

    @Override
    public CommonResponse save(HealthInfoEntity input) {
        healthInfoRepository.save(input);
        return CommonResponse.success("保存成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        healthInfoRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(HealthInfoPageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum()-1, input.getPageSize());
        Page<HealthInfoEntity> result = healthInfoRepository.findByCriteria(
                input.getTitle(),
                input.getType(),
                input.getCreateBy(),
                input.getUpdateBy(),
                input.getRemark(),
                pageable);
        return CommonResponse.success(new PagedModel<>(result));
    }

    @Override
    public CommonResponse selectByPageCollect(HealthInfoPageDto input) {
        if (UserContext.getId() != null) {
            List<HealthInfoCollectEntity> collectList = healthInfoCollectRepository.findByUserId(UserContext.getId());
            Pageable pageable = PageRequest.of(input.getPageNum() - 1, input.getPageSize());
            Page<HealthInfoEntity> result = healthInfoRepository.findByIdIn(
                    collectList.stream().map(HealthInfoCollectEntity::getHealthInfoId).collect(Collectors.toList()),
                    pageable);
            return CommonResponse.success(new PagedModel<>(result));
        }
        return CommonResponse.fail("查询失败");
    }

    @Override
    public CommonResponse selectLatest() {
        List<HealthInfoEntity> result = healthInfoRepository.findTop5ByOrderByCreateTimeDesc();
        return CommonResponse.success(result);
    }

}
