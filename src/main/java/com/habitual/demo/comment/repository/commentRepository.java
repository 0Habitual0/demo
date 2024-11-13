package com.habitual.demo.comment.repository;

import com.habitual.demo.comment.entity.commentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<commentEntity, Long> {

}
