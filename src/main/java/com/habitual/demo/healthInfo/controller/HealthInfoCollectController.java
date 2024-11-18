package com.habitual.demo.healthInfo.controller;

import com.habitual.demo.healthInfo.service.HealthInfoCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层 健康资讯收藏
 */
@RestController
@RequestMapping("/healthInfoCollect")
public class HealthInfoCollectController {

    private static final Logger log = LoggerFactory.getLogger(HealthInfoCollectController.class);

    @Autowired
    private HealthInfoCollectService healthInfoCollectService;



}
