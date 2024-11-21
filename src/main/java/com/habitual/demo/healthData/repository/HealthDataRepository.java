package com.habitual.demo.healthData.repository;

import com.habitual.demo.healthData.entity.HealthDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface HealthDataRepository extends JpaRepository<HealthDataEntity, Long> {

    @Query("SELECT h FROM HealthDataEntity h WHERE " +
            "(:userId IS NULL OR h.userId = :userId) AND " +
            "(:createAfter IS NULL OR h.createTime >= :createAfter) AND " +
            "(:createBefore IS NULL OR h.createTime <= :createBefore) AND " +
            "(:remark IS NULL OR h.remark LIKE %:remark%)")
    Page<HealthDataEntity> findByCriteria(@Param("userId") Long userId,
                                          @Param("createAfter") Date createAfter,
                                          @Param("createBefore") Date createBefore,
                                          @Param("remark") String remark,
                                          Pageable pageable);

}
