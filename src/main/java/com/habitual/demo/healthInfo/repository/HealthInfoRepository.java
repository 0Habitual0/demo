package com.habitual.demo.healthInfo.repository;

import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HealthInfoRepository extends JpaRepository<HealthInfoEntity, Long> {

    @Query("SELECT h FROM HealthInfoEntity h WHERE (:title IS NULL OR h.title = :title) AND (:type IS NULL OR h.type = :type)")
    Page<HealthInfoEntity> findByTitleAndType(@Param("title") String title, @Param("type") String type, Pageable pageable);

}
