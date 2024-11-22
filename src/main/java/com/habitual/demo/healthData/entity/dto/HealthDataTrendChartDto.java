package com.habitual.demo.healthData.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HealthDataTrendChartDto {

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date date;

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
