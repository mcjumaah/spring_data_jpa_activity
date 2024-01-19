package com.intelliseven.spring_data_jpa_activity.service;

import java.util.List;
import java.util.Optional;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

public interface HearingService {
  HearingEntity save(HearingEntity hearing);

  List<HearingEntity> findAll();

  Optional<HearingEntity> findOne(Long id);

  boolean isExists(Long id);

  HearingEntity partialUpdate(Long id, HearingEntity hearingEntity);

  void delete(Long id);
}
