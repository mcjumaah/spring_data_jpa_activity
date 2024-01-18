package com.intelliseven.spring_data_jpa_activity.service;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;

public interface HearingService {
  HearingEntity createHearing(HearingEntity hearing);
}
