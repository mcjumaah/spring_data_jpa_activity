package com.intelliseven.spring_data_jpa_activity.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class HearingController {

  private HearingService hearingService;

  public HearingController(HearingService hearingService) {
    this.hearingService = hearingService;
  }

  @PostMapping("/hearings")
  public ResponseEntity<HearingDto> createHearing(@RequestBody HearingDto hearingDtoRequest) {
    return hearingService.createHearing(hearingDtoRequest);
  }

  @GetMapping("/hearings")
  public Page<HearingDto> listHearings(Pageable pageable) {
    return hearingService.listHearings(pageable);
  }

  @GetMapping("/hearings/{id}")
  public ResponseEntity<HearingDto> getHearing(@PathVariable("id") Long id) {
    return hearingService.getHearing(id);
  }

  @PutMapping("/hearings/{id}")
  public ResponseEntity<HearingDto> fullUpdateHearing(@PathVariable("id") Long id,
      @RequestBody HearingDto hearingDtoRequest) {
    return hearingService.fullUpdateHearing(id, hearingDtoRequest);
  }

  @PatchMapping("/hearings/{id}")
  public ResponseEntity<HearingDto> partialUpdateHearing(@PathVariable("id") Long id,
      @RequestBody HearingDto hearingDtoRequest) {
    return hearingService.partialUpdateHearing(id, hearingDtoRequest);
  }

  // @DeleteMapping("/hearings/{id}")
  // public ResponseEntity<?> deleteHearing(@PathVariable("id") Long id) {

  // if (!hearingService.isExists(id)) {
  // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  // }

  // hearingService.delete(id);
  // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  // }

}
