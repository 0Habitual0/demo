package com.habitual.demo.message.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 消息
 */
@Getter
@Setter
@Entity
@Table(name = "message")
public class MessageEntity extends BaseEntity {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 父留言id
     */
    private Long parentId;

    /**
     * 关联主键id
     */
    private Long relatedId;

    /**
     * 用户关联主键id
     */
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private String type;

}
