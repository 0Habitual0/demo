package com.habitual.demo.healthInfo.repository;

import com.habitual.demo.healthInfo.entity.HealthInfoCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据访问层JPA 健康资讯评论
 */
public interface HealthInfoCommentRepository extends JpaRepository<HealthInfoCommentEntity, Long> {
}
