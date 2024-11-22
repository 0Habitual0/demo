package com.habitual.demo.healthData.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.security.UserContext;
import com.habitual.demo.healthData.entity.HealthDataEntity;
import com.habitual.demo.healthData.entity.dto.HealthDataPageDto;
import com.habitual.demo.healthData.entity.dto.HealthDataTrendChartDto;
import com.habitual.demo.healthData.repository.HealthDataRepository;
import com.habitual.demo.healthData.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public CommonResponse trendChart() {
        List<HealthDataEntity> data = healthDataRepository.findAll();
        // 按日期分类
        Map<Date, List<HealthDataEntity>> groupedByDate = data.stream()
                .collect(Collectors.groupingBy(entity -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(entity.getCreateTime());
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    return cal.getTime();
                }));

        // 计算每个日期的平均值
        List<HealthDataTrendChartDto> output = new ArrayList<>();

        for (Map.Entry<Date, List<HealthDataEntity>> entry : groupedByDate.entrySet()) {
            Date date = entry.getKey();
            List<HealthDataEntity> entities = entry.getValue();

            double avgVitalCapacity = entities.stream().mapToLong(HealthDataEntity::getVitalCapacity).average().orElse(0.0);
            double avgBloodSugar = entities.stream().mapToLong(HealthDataEntity::getBloodSugar).average().orElse(0.0);
            double avgBloodFat = entities.stream().mapToLong(HealthDataEntity::getBloodFat).average().orElse(0.0);
            double avgBloodPressure = entities.stream().mapToLong(HealthDataEntity::getBloodPressure).average().orElse(0.0);
            double avgCholesterol = entities.stream().mapToLong(HealthDataEntity::getCholesterol).average().orElse(0.0);

            HealthDataTrendChartDto dto = new HealthDataTrendChartDto();
            dto.setDate(date);
            dto.setVitalCapacity((long) avgVitalCapacity);
            dto.setBloodSugar((long) avgBloodSugar);
            dto.setBloodFat((long) avgBloodFat);
            dto.setBloodPressure((long) avgBloodPressure);
            dto.setCholesterol((long) avgCholesterol);

            output.add(dto);
        }
        output = output.stream() .sorted(Comparator.comparing(HealthDataTrendChartDto::getDate)) .collect(Collectors.toList());
        return CommonResponse.success(output);
    }

}
