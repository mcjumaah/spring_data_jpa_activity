package com.intelliseven.spring_data_jpa_activity.service.impl;

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

}
