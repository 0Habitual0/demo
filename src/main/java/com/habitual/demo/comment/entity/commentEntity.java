package com.habitual.demo.comment.entity;

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
@Table(name = "comment")
public class commentEntity extends BaseEntity {

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
     * 关联主键id (健康资讯id)
     */
    private Long associationId;

    /**
     * 评论内容
     */
    private String comment;

}
