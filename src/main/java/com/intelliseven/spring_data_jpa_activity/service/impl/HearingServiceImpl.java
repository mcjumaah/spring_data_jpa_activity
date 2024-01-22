package com.intelliseven.spring_data_jpa_activity.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.mapper.Mapper;
import com.intelliseven.spring_data_jpa_activity.persistence.repository.HearingRepo;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;
import com.intelliseven.spring_data_jpa_activity.service.HearingService;

@Service
public class HearingServiceImpl implements HearingService {

  private HearingRepo hearingRepo;
  private Mapper<HearingEntity, HearingDto> hearingMapper;

  public HearingServiceImpl(HearingRepo hearingRepo,
      Mapper<HearingEntity, HearingDto> hearingMapper) {
    this.hearingRepo = hearingRepo;
    this.hearingMapper = hearingMapper;
  }

  @Override
  public ResponseEntity<HearingDto> createHearing(HearingDto hearingDtoRequest) {
    HearingEntity hearingEntity = hearingMapper.mapFrom(hearingDtoRequest);
    HearingEntity savedHearingEntity = hearingRepo.save(hearingEntity);
    return new ResponseEntity<>(hearingMapper.mapTo(savedHearingEntity), HttpStatus.CREATED);
  }

  @Override
  public Page<HearingDto> listHearings(Pageable pageable) {
    Page<HearingEntity> hearings = hearingRepo.findAll(pageable);
    return hearings.map(hearingMapper::mapTo);
  }

  @Override
  public ResponseEntity<HearingDto> getHearing(Long id) {
    Optional<HearingEntity> foundHearing = hearingRepo.findById(id);
    return foundHearing.map(hearingEntity -> {
      HearingDto hearingDto = hearingMapper.mapTo(hearingEntity);
      return new ResponseEntity<>(hearingDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public ResponseEntity<HearingDto> fullUpdateHearing(Long id, HearingDto hearingDtoRequest) {
    if (!hearingRepo.existsById(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    hearingDtoRequest.setId(id);
    HearingEntity hearingEntity = hearingMapper.mapFrom(hearingDtoRequest);
    HearingEntity savedHearingEntity = hearingRepo.save(hearingEntity);
    return new ResponseEntity<>(hearingMapper.mapTo(savedHearingEntity), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HearingDto> partialUpdateHearing(Long id, HearingDto hearingDtoRequest) {
    if (!hearingRepo.existsById(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    HearingEntity hearingEntityRequest = hearingMapper.mapFrom(hearingDtoRequest);
    hearingEntityRequest.setId(id);

    HearingEntity updatedHearingEntity = hearingRepo.findById(id).map(existingHearing -> {
      Optional.ofNullable(hearingEntityRequest.getCaseName())
          .ifPresent(existingHearing::setCaseName);
      Optional.ofNullable(hearingEntityRequest.getDate()).ifPresent(existingHearing::setDate);
      Optional.ofNullable(hearingEntityRequest.getStatus()).ifPresent(existingHearing::setStatus);
      Optional.ofNullable(hearingEntityRequest.getIncident())
          .ifPresent(existingHearing::setIncident);
      Optional.ofNullable(hearingEntityRequest.getProceeding())
          .ifPresent(existingHearing::setProceeding);
      return hearingRepo.save(existingHearing);
    }).orElseThrow(() -> new RuntimeException("Hearing does not exist"));

    return new ResponseEntity<>(hearingMapper.mapTo(updatedHearingEntity), HttpStatus.OK);
  }

  @Override
  public void delete(Long id) {
    hearingRepo.deleteById(id);
  }
}
