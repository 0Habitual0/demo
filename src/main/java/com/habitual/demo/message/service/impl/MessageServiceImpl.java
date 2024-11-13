package com.habitual.demo.message.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.message.entity.MessageEntity;
import com.habitual.demo.message.entity.dto.MessagePageDto;
import com.habitual.demo.message.repository.MessageRepository;
import com.habitual.demo.message.service.MessageService;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 消息
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public CommonResponse save(MessageEntity input) {
        messageRepository.save(input);
        return CommonResponse.success("保存成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        messageRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(MessagePageDto input) {
        return null;
    }

}
