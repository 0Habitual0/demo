package com.habitual.demo.common.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

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
        return "current_user"; // 替换为实际实现 TODO
    }

}
