package com.intelliseven.spring_data_jpa_activity.service;

import java.util.List;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

public interface HearingService {
  HearingEntity createHearing(HearingEntity hearing);

  List<HearingEntity> findAll();
}
