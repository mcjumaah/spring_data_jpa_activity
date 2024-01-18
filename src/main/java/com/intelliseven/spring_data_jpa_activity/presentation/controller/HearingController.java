package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.mapper.Mapper;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api")
public class HearingController {

  private HearingService hearingService;
  private Mapper<HearingEntity, HearingDto> hearingMapper;

  public HearingController(HearingService hearingService, Mapper<HearingEntity, HearingDto> hearingMapper) {
    this.hearingService = hearingService;
    this.hearingMapper = hearingMapper;
  }

  @PostMapping(path = "/hearings")
  public HearingDto creatHearing(@RequestBody HearingDto hearing) {
    HearingEntity hearingEntity = hearingMapper.mapFrom(hearing);
    HearingEntity savedHearingEntity = hearingService.createHearing(hearingEntity);
    return hearingMapper.mapTo(savedHearingEntity);
  }

}
