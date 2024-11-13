package com.habitual.demo.message.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagePageDto {

    private Long relatedId;

    private Long userId;

    private String type;

    private int pageNum;

    private int pageSize;

}
