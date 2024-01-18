package com.intelliseven.spring_data_jpa_activity.presentation.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HearingDto {
  private Long id;

  private String caseName;
  private LocalDate date;
  private String status;
  private String incident;
  private String proceeding;

  public HearingDto(String caseName, LocalDate date, String status, String incident, String proceeding) {
    this.caseName = caseName;
    this.date = date;
    this.status = status;
    this.incident = incident;
    this.proceeding = proceeding;
  }

}
