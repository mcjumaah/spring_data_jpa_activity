package com.intelliseven.spring_data_jpa_activity.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.boot.env.ConfigTreePropertySource.Option;
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
  public HearingEntity save(HearingEntity hearingEntity) {
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

  @Override
  public boolean isExists(Long id) {
    return hearingRepo.existsById(id);
  }

  @Override
  public HearingEntity partialUpdate(Long id, HearingEntity hearingEntity) {
    hearingEntity.setId(id);

    return hearingRepo.findById(id).map(existingHearing -> {
      Optional.ofNullable(hearingEntity.getCaseName()).ifPresent(existingHearing::setCaseName);
      Optional.ofNullable(hearingEntity.getDate()).ifPresent(existingHearing::setDate);
      Optional.ofNullable(hearingEntity.getStatus()).ifPresent(existingHearing::setStatus);
      Optional.ofNullable(hearingEntity.getIncident()).ifPresent(existingHearing::setIncident);
      Optional.ofNullable(hearingEntity.getProceeding()).ifPresent(existingHearing::setProceeding);
      return hearingRepo.save(existingHearing);
    }).orElseThrow(() -> new RuntimeException("Hearing does not exist"));
  }
}
