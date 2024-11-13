package com.habitual.demo.healthInfo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 健康咨询
 */
@Getter
@Setter
@Entity
@Table(name = "health_info")
public class HealthInfoEntity extends BaseEntity {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 种类 (运动建议/饮食推荐)
     */
    private String type;

}
