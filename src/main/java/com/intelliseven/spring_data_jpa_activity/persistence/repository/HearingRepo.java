package com.intelliseven.spring_data_jpa_activity.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.Hearing;

@Repository
public interface HearingRepo extends JpaRepository<Hearing, Long> {

}
