package com.intelliseven.spring_data_jpa_activity.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.Hearing;

@Service
public class HearingService {
  public List<Hearing> getHearings() {
    return List.of(new Hearing(1L, "Juan vs. Pipol of da Pelepens", LocalDate.of(2000, Month.AUGUST, 18),
        "PROCEED", "Sample Incident ngaya", "Sample Proceeding kuno"));
  }
}
