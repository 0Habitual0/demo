package com.habitual.demo.healthInfo.repository;

import com.habitual.demo.healthInfo.entity.HealthInfoCollectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 数据访问层JPA 健康资讯收藏
 */
public interface HealthInfoCollectRepository extends JpaRepository<HealthInfoCollectEntity, Long> {

    HealthInfoCollectEntity findByUserIdAndHealthInfoId(Long userId, Long healthInfoId);

    List<HealthInfoCollectEntity> findByUserId(Long healthInfoId);

}
