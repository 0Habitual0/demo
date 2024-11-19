package com.habitual.demo.message.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagePageDto {

    private String content;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

}
