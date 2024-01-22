package com.intelliseven.spring_data_jpa_activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;

public interface HearingService {
  ResponseEntity<HearingDto> createHearing(HearingDto hearingDtoRequest);

  Page<HearingDto> listHearings(Pageable pageable);

  ResponseEntity<HearingDto> getHearing(Long id);

  ResponseEntity<HearingDto> fullUpdateHearing(Long id, HearingDto hearingDtoRequest);

  ResponseEntity<HearingDto> partialUpdateHearing(Long id, HearingDto hearingDtoRequest);

  void delete(Long id);
}
