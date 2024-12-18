package com.habitual.demo.healthInfo.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;
import com.habitual.demo.healthInfo.service.HealthInfoCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 健康资讯收藏
 */
@RestController
@RequestMapping("/healthInfoCollect")
public class HealthInfoCollectController {

    private static final Logger log = LoggerFactory.getLogger(HealthInfoCollectController.class);

    @Autowired
    private HealthInfoCollectService healthInfoCollectService;

    /**
     * 收藏/取消收藏
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody HealthInfoCollectEntity input) {
        return healthInfoCollectService.collect(input);
    }

    /**
     * 查询是否收藏
     */
    @GetMapping("isCollect")
    public CommonResponse isCollect(@RequestParam Long healthInfoId) {
        return healthInfoCollectService.isCollect(healthInfoId);
    }

}
