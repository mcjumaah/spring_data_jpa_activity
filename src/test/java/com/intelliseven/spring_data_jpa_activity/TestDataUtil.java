package com.intelliseven.spring_data_jpa_activity;

import java.time.LocalDate;
import java.time.Month;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;

public final class TestDataUtil {
  private TestDataUtil() {

  }

  public static HearingEntity createTestHearingEntityA() {
    return HearingEntity.builder().id(1L).caseName("Test Case Name vs. Pipol of da Pelepens")
        .date(LocalDate.of(2000, Month.AUGUST, 18)).status("PROCEED").incident("Sample test incident naman ngaya")
        .proceeding("Tas test proceeding man kuno ading usad").build();
  }

  public static HearingDto createTestHearingDtoA() {
    return HearingDto.builder().id(1L).caseName("Test Case Name vs. Pipol of da Pelepens")
        .date(LocalDate.of(2000, Month.AUGUST, 18)).status("PROCEED").incident("Sample test incident naman ngaya")
        .proceeding("Tas test proceeding man kuno ading usad").build();
  }

  public static HearingEntity createTestHearingEntityB() {
    return HearingEntity.builder().id(2L).caseName("Test Case Name B vs. Pipol of da Pelepens")
        .date(LocalDate.of(2001, Month.SEPTEMBER, 19)).status("DONE").incident("Sample test incident B")
        .proceeding("Test proceeding B").build();
  }

  public static HearingDto createTestHearingDtoB() {
    return HearingDto.builder().id(2L).caseName("Test Case Name B vs. Pipol of da Pelepens")
        .date(LocalDate.of(2001, Month.SEPTEMBER, 19)).status("DONE").incident("Sample test incident B")
        .proceeding("Test proceeding B").build();
  }

  public static HearingEntity createTestHearingEntityC() {
    return HearingEntity.builder().id(3L).caseName("Test Case Name C vs. Pipol of da Pelepens")
        .date(LocalDate.of(2002, Month.OCTOBER, 20)).status("CANCELLED").incident("Sample test incident C")
        .proceeding("Test proceeding C").build();
  }

  public static HearingDto createTestHearingDtoC() {
    return HearingDto.builder().id(3L).caseName("Test Case Name C vs. Pipol of da Pelepens")
        .date(LocalDate.of(2002, Month.OCTOBER, 20)).status("CANCELLED").incident("Sample test incident C")
        .proceeding("Test proceeding C").build();
  }
}
