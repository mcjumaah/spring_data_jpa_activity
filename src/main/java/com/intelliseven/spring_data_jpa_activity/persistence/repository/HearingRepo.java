package com.intelliseven.spring_data_jpa_activity.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

@Repository
public interface HearingRepo extends JpaRepository<HearingEntity, Long> {
}
