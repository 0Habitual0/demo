package com.habitual.demo.message.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MessageVo {

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;

    /**
     * 父消息id
     */
    private Long parentId;

    /**
     * 用户关联主键id
     */
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 子消息
     */
    private List<MessageVo> children;

}
