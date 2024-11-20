package com.habitual.demo.message.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.security.UserContext;
import com.habitual.demo.message.entity.MessageEntity;
import com.habitual.demo.message.entity.dto.MessagePageDto;
import com.habitual.demo.message.entity.vo.MessageVo;
import com.habitual.demo.message.repository.MessageRepository;
import com.habitual.demo.message.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务层实现 消息
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public CommonResponse save(MessageEntity input) {
        input.setUserId(UserContext.getId());
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
        Pageable pageable = PageRequest.of(input.getPageNum()-1, input.getPageSize());
        Page<MessageEntity> result = messageRepository.findByCriteria(
                input.getContent(),
                input.getCreateBy(),
                input.getUpdateBy(),
                input.getRemark(),
                pageable);
        return CommonResponse.success(new PagedModel<>(result));
    }

    @Override
    public CommonResponse selectByUser() {
        // 获取当前用户的所有消息
        List<MessageEntity> messageEntities = messageRepository.findByUserIdAndParentIdOrderByCreateTimeDesc(UserContext.getId(), null);
        List<MessageVo> result = new ArrayList<>();

        // 复制父消息的属性
        for (MessageEntity messageEntity : messageEntities) {
            MessageVo messageVo = new MessageVo();
            BeanUtils.copyProperties(messageEntity, messageVo);
            result.add(messageVo);
        }

        // 获取所有父消息的子消息
        List<MessageEntity> childMessageEntities = messageRepository.findByParentIdInOrderByCreateTimeDesc(
                messageEntities.stream()
                        .map(MessageEntity::getId)
                        .collect(Collectors.toList()));

        // 将子消息添加到对应的父消息中
        for (MessageEntity childMessageEntity : childMessageEntities) {
            for (MessageVo parentMessageVo : result) {
                if (parentMessageVo.getId().equals(childMessageEntity.getParentId())) {
                    if (parentMessageVo.getChildren() == null) {
                        parentMessageVo.setChildren(new ArrayList<>());
                    }
                    MessageVo childMessageVo = new MessageVo();
                    BeanUtils.copyProperties(childMessageEntity, childMessageVo);
                    parentMessageVo.getChildren().add(childMessageVo);
                }
            }
        }

        return CommonResponse.success(result);
    }

}
