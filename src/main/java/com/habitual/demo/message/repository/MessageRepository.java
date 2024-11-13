package com.habitual.demo.message.repository;

import com.habitual.demo.message.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据访问层JPA 消息
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}
