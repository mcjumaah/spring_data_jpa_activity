package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.Hearing;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "api/hearing")
public class HearingController {
  private final HearingService hearingService;

  public HearingController(HearingService hearingService) {
    this.hearingService = hearingService;
  }

  @GetMapping()
  public List<Hearing> getHearings() {
    return hearingService.getHearings();
  }

}
