package com.habitual.demo.healthInfo.repository;

import com.habitual.demo.healthInfo.entity.HealthInfoCommentEntity;
import com.habitual.demo.healthInfo.entity.HealthInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 数据访问层JPA 健康资讯评论
 */
public interface HealthInfoCommentRepository extends JpaRepository<HealthInfoCommentEntity, Long> {

    @Query("SELECT h FROM HealthInfoCommentEntity h WHERE" +
            "(:content IS NULL OR h.content LIKE %:content%) AND" +
            "(:createBy IS NULL OR h.createBy LIKE %:createBy%) AND" +
            "(:updateBy IS NULL OR h.updateBy LIKE %:updateBy%) AND" +
            "(:remark IS NULL OR h.remark LIKE %:remark%)")
    Page<HealthInfoCommentEntity> findByCriteria(@Param("content") String content,
                                          @Param("createBy") String createBy,
                                          @Param("updateBy") String updateBy,
                                          @Param("remark") String remark,
                                          Pageable pageable);

    List<HealthInfoCommentEntity> findByHealthInfoIdOrderByCreateTimeDesc(Long healthInfoId);

}
