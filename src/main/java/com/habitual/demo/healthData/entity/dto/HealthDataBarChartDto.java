package com.habitual.demo.healthData.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthDataBarChartDto {

    /**
     * 肺活量(ml)平均值
     */
    private Long vitalCapacity;

    /**
     * 血糖(mmol/L)平均值
     */
    private Long bloodSugar;

    /**
     * 血脂(mmol/L)平均值
     */
    private Long bloodFat;

    /**
     * 血压(mmHg)平均值
     */
    private Long bloodPressure;

    /**
     * 胆固醇(mmol/L)平均值
     */
    private Long cholesterol;

}
