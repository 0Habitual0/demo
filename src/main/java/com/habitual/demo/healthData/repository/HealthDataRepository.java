package com.habitual.demo.healthData.repository;

import com.habitual.demo.healthData.entity.HealthDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthDataRepository extends JpaRepository<HealthDataEntity, Long> {

}
