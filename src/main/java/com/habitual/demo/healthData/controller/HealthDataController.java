package com.habitual.demo.healthData.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthData.entity.HealthDataEntity;
import com.habitual.demo.healthData.entity.dto.HealthDataPageDto;
import com.habitual.demo.healthData.service.HealthDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 健康数据
 */
@RestController
@RequestMapping("/healthData")
public class HealthDataController {

    private static final Logger log = LoggerFactory.getLogger(HealthDataController.class);

    @Autowired
    private HealthDataService healthDataService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody HealthDataEntity input) {
        return healthDataService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return healthDataService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody HealthDataPageDto input) {
        return healthDataService.selectByPage(input);
    }

    /**
     * 总体趋势图
     */
    @PostMapping("trendChart")
    public CommonResponse trendChart() {
        return healthDataService.trendChart();
    }

}
