package com.intelliseven.spring_data_jpa_activity.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

public interface HearingService {
  HearingEntity save(HearingEntity hearing);

  List<HearingEntity> findAll();

  Page<HearingEntity> findAll(Pageable pageable);

  Optional<HearingEntity> findOne(Long id);

  boolean isExists(Long id);

  HearingEntity partialUpdate(Long id, HearingEntity hearingEntity);

  void delete(Long id);
}
