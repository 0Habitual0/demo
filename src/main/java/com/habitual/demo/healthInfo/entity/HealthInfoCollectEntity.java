package com.habitual.demo.healthInfo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 健康资讯收藏
 */
@Getter
@Setter
@Entity
@Table(name = "health_info_collect")
public class HealthInfoCollectEntity extends BaseEntity {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 健康资讯id
     */
    private Long healthInfoId;

}
