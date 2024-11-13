package com.habitual.demo.message.repository;

import com.habitual.demo.message.entity.messageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface messageRepository extends JpaRepository<messageEntity, Long> {

}
