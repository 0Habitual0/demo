package com.habitual.demo.healthInfo.repository;

import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 数据访问层JPA 健康资讯
 */
public interface HealthInfoRepository extends JpaRepository<HealthInfoEntity, Long> {

    @Query("SELECT h FROM HealthInfoEntity h WHERE" +
            "(:title IS NULL OR h.title LIKE %:title%) AND" +
            "(:type IS NULL OR h.type = :type) AND " +
            "(:createBy IS NULL OR h.createBy LIKE %:createBy%) AND" +
            "(:updateBy IS NULL OR h.updateBy LIKE %:updateBy%) AND" +
            "(:remark IS NULL OR h.remark LIKE %:remark%)")
    Page<HealthInfoEntity> findByCriteria(@Param("title") String title,
                                          @Param("type") String type,
                                          @Param("createBy") String createBy,
                                          @Param("updateBy") String updateBy,
                                          @Param("remark") String remark,
                                          Pageable pageable);


    Page<HealthInfoEntity> findByIdIn(List<Long> userIds, Pageable pageable);

    List<HealthInfoEntity> findTop5ByOrderByCreateTimeDesc();

}
