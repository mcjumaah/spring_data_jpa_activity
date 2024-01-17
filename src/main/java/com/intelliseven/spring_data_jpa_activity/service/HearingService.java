package com.intelliseven.spring_data_jpa_activity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.Hearing;
import com.intelliseven.spring_data_jpa_activity.persistence.repository.HearingRepo;

@Service
public class HearingService {
  private final HearingRepo hearingRepo;

  public HearingService(HearingRepo hearingRepo) {
    this.hearingRepo = hearingRepo;
  }

  public List<Hearing> getHearings() {
    return hearingRepo.findAll();
  }
}
