package com.habitual.demo.message.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.message.entity.MessageEntity;
import com.habitual.demo.message.entity.dto.MessagePageDto;

/**
 * 业务层 消息
 */
public interface MessageService {

    CommonResponse save(MessageEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(MessagePageDto input);

    CommonResponse selectByUser();

}
