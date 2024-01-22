package com.intelliseven.spring_data_jpa_activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;

public interface HearingService {
  ResponseEntity<HearingDto> createHearing(HearingDto hearingDtoRequest);

  Page<HearingDto> listHearings(Pageable pageable);

  ResponseEntity<HearingDto> getHearing(Long id);

  ResponseEntity<HearingDto> fullUpdateHearing(Long id, HearingDto hearingDtoRequest);

  HearingEntity partialUpdate(Long id, HearingEntity hearingEntity);

  void delete(Long id);
}
