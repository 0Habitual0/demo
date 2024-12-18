package com.habitual.demo.healthInfo.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import com.habitual.demo.healthInfo.entity.dto.HealthInfoPageDto;
import com.habitual.demo.healthInfo.service.HealthInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 健康资讯
 */
@RestController
@RequestMapping("/healthInfo")
public class HealthInfoController {

    private static final Logger log = LoggerFactory.getLogger(HealthInfoController.class);

    @Autowired
    private HealthInfoService healthInfoService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody HealthInfoEntity input) {
        return healthInfoService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return healthInfoService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody HealthInfoPageDto input) {
        return healthInfoService.selectByPage(input);
    }

    /**
     * 分页查询 用户收藏
     */
    @PostMapping("selectByPageCollect")
    public CommonResponse selectByPageCollect(@RequestBody HealthInfoPageDto input) {
        return healthInfoService.selectByPageCollect(input);
    }

    /**
     * 查询最新五条
     */
    @GetMapping("selectLatest")
    public CommonResponse selectLatest() {
        return healthInfoService.selectLatest();
    }

}
