package com.habitual.demo.healthInfo.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthInfoCommentPageDto {

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

}
