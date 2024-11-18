package com.habitual.demo.healthInfo.repository;

import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据访问层JPA 健康资讯收藏
 */
public interface HealthInfoCollectRepository extends JpaRepository<HealthInfoEntity, Long> {

}
