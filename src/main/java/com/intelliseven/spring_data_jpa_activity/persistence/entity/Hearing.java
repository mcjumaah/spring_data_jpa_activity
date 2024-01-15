package com.intelliseven.spring_data_jpa_activity.persistence.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hearing {
  private long id;
  private String caseName;
  private LocalDate date;
  private String status;
  private String incident;
  private String proceeding;

  public Hearing(String caseName, LocalDate date, String status, String incident, String proceeding) {
    this.caseName = caseName;
    this.date = date;
    this.status = status;
    this.incident = incident;
    this.proceeding = proceeding;
  }
}
