package com.habitual.demo.healthInfo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 健康资讯评论
 */
@Getter
@Setter
@Entity
@Table(name = "health_Info_comment")
public class HealthInfoCommentEntity extends BaseEntity {

    /**
     * 序列号版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 健康资讯id
     */
    private Long healthInfoId;

    /**
     * 用户关联主键id
     */
    private Long userId;

    /**
     * 内容
     */
    @Column(columnDefinition = "LONGTEXT")
    private String content;

}
