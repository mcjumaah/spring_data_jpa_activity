package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.mapper.Mapper;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class HearingController {

  private HearingService hearingService;
  private Mapper<HearingEntity, HearingDto> hearingMapper;

  public HearingController(HearingService hearingService,
      Mapper<HearingEntity, HearingDto> hearingMapper) {
    this.hearingService = hearingService;
    this.hearingMapper = hearingMapper;
  }

  @PostMapping("/hearings")
  public ResponseEntity<HearingDto> creatHearing(@RequestBody HearingDto hearingDtoRequest) {
    return hearingService.createHearing(hearingDtoRequest);
  }

  @GetMapping("/hearings")
  public Page<HearingDto> listHearings(Pageable pageable) {
    return hearingService.listHearings(pageable);
  }

  @GetMapping("/hearings/{id}")
  public ResponseEntity<HearingDto> getHearing(@PathVariable("id") Long id) {
    Optional<HearingEntity> foundHearing = hearingService.findOne(id);
    return foundHearing.map(hearingEntity -> {
      HearingDto hearingDto = hearingMapper.mapTo(hearingEntity);
      return new ResponseEntity<>(hearingDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // @PutMapping("/hearings/{id}")
  // public ResponseEntity<HearingDto> fullUpdateHearing(@PathVariable("id") Long id,
  // @RequestBody HearingDto hearingDto) {

  // if (!hearingService.isExists(id)) {
  // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  // }
  // hearingDto.setId(id);
  // HearingEntity hearingEntity = hearingMapper.mapFrom(hearingDto);
  // HearingEntity savedHearingEntity = hearingService.save(hearingEntity);
  // return new ResponseEntity<>(hearingMapper.mapTo(savedHearingEntity), HttpStatus.OK);
  // }

  @PatchMapping("/hearings/{id}")
  public ResponseEntity<HearingDto> partialUpdateHearing(@PathVariable("id") Long id,
      @RequestBody HearingDto hearingDto) {

    if (!hearingService.isExists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    HearingEntity hearingEntity = hearingMapper.mapFrom(hearingDto);
    HearingEntity updatedHearing = hearingService.partialUpdate(id, hearingEntity);
    return new ResponseEntity<>(hearingMapper.mapTo(updatedHearing), HttpStatus.OK);

  }

  @DeleteMapping("/hearings/{id}")
  public ResponseEntity<?> deleteHearing(@PathVariable("id") Long id) {

    if (!hearingService.isExists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    hearingService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
