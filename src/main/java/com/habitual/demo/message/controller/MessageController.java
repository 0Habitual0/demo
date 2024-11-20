package com.habitual.demo.message.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.message.entity.MessageEntity;
import com.habitual.demo.message.entity.dto.MessagePageDto;
import com.habitual.demo.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 消息
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody MessageEntity input) {
        return messageService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return messageService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody MessagePageDto input) {
        return messageService.selectByPage(input);
    }

    /**
     * 根据用户查询
     */
    @GetMapping("selectByUser")
    public CommonResponse selectByUser() {
        return messageService.selectByUser();
    }

}
