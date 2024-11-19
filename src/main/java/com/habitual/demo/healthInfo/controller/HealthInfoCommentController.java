package com.habitual.demo.healthInfo.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoCommentEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoCommentPageDto;
import com.habitual.demo.healthInfo.service.HealthInfoCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 健康资讯评论
 */
@RestController
@RequestMapping("/healthInfoComment")
public class HealthInfoCommentController {

    private static final Logger log = LoggerFactory.getLogger(HealthInfoCollectController.class);

    @Autowired
    private HealthInfoCommentService healthInfoCommentService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody HealthInfoCommentEntity input) {
        return healthInfoCommentService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return healthInfoCommentService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody HealthInfoCommentPageDto input) {
        return healthInfoCommentService.selectByPage(input);
    }

    /**
     * 根据健康资讯查询
     */
    @GetMapping("selectByHealthInfo")
    public CommonResponse selectByHealthInfo(@RequestBody Long healthInfoId) {
        return healthInfoCommentService.selectByHealthInfo(healthInfoId);
    }

}
