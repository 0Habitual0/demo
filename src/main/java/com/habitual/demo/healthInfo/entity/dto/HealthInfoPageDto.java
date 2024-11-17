package com.habitual.demo.healthInfo.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthInfoPageDto {

    private String title;

    private String type;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

}
