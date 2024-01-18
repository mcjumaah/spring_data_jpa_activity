package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.mapper.Mapper;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api")
public class HearingController {

  private HearingService hearingService;
  private Mapper<HearingEntity, HearingDto> hearingMapper;

  public HearingController(HearingService hearingService,
      Mapper<HearingEntity, HearingDto> hearingMapper) {
    this.hearingService = hearingService;
    this.hearingMapper = hearingMapper;
  }

  @PostMapping(path = "/hearings")
  public ResponseEntity<HearingDto> creatHearing(@RequestBody HearingDto hearing) {
    HearingEntity hearingEntity = hearingMapper.mapFrom(hearing);
    HearingEntity savedHearingEntity = hearingService.createHearing(hearingEntity);
    return new ResponseEntity<>(hearingMapper.mapTo(savedHearingEntity), HttpStatus.CREATED);
  }

  @GetMapping("/hearings")
  public List<HearingDto> listHearings() {
    List<HearingEntity> hearings = hearingService.findAll();
    return hearings.stream().map(hearingMapper::mapTo).collect(Collectors.toList());
  }

}
