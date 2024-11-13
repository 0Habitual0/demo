package com.habitual.demo.healthData.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 健康数据
 */
@Getter
@Setter
@Entity
@Table(name = "health_data")
public class HealthDataEntity extends BaseEntity {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户关联主键id
     */
    private Long userId;

    /**
     * 肺活量(ml)
     */
    private Long vitalCapacity;

    /**
     * 血糖(mmol/L)
     */
    private Long bloodSugar;


    /**
     * 血脂(mmol/L)
     */
    private Long bloodFat;

    /**
     * 血压(mmHg)
     */
    private Long bloodPressure;

    /**
     * 胆固醇(mmol/L)
     */
    private Long cholesterol;

}
