package com.habitual.demo.message.repository;

import com.habitual.demo.message.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据访问层JPA 消息
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT h FROM MessageEntity h WHERE" +
            "(:content IS NULL OR h.content LIKE %:content%) AND" +
            "(:createBy IS NULL OR h.createBy LIKE %:createBy%) AND" +
            "(:updateBy IS NULL OR h.updateBy LIKE %:updateBy%) AND" +
            "(:remark IS NULL OR h.remark LIKE %:remark%)")
    Page<MessageEntity> findByCriteria(@Param("content") String content,
                                                 @Param("createBy") String createBy,
                                                 @Param("updateBy") String updateBy,
                                                 @Param("remark") String remark,
                                                 Pageable pageable);

}
