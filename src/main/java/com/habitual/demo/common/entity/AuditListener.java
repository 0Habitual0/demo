package com.habitual.demo.common.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

/**
 * 监听器 用于自动补充时间与用户信息
 */
public class AuditListener {

    @PrePersist
    public void setCreatedAtAndCreatedBy(BaseEntity entity) {
        entity.setCreateTime(new Date());
        entity.setCreateBy(getCurrentUser());
    }

    @PreUpdate
    public void setUpdatedAtAndUpdatedBy(BaseEntity entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateBy(getCurrentUser());
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            return authentication.getName();
        }
        return "未知用户";
    }

}
