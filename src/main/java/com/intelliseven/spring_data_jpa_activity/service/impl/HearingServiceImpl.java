package com.intelliseven.spring_data_jpa_activity.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.repository.HearingRepo;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

@Service
public class HearingServiceImpl implements HearingService {

  private HearingRepo hearingRepo;

  public HearingServiceImpl(HearingRepo hearingRepo) {
    this.hearingRepo = hearingRepo;
  }

  @Override
  public HearingEntity createHearing(HearingEntity hearingEntity) {
    return hearingRepo.save(hearingEntity);
  }

  @Override
  public List<HearingEntity> findAll() {
    return StreamSupport.stream(hearingRepo.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<HearingEntity> findOne(Long id) {
    return hearingRepo.findById(id);
  }

}
