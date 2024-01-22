package com.intelliseven.spring_data_jpa_activity.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Override
  public void delete(Long id) {
    hearingRepo.deleteById(id);
  }
}
