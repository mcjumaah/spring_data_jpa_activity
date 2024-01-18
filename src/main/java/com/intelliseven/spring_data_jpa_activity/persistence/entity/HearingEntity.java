package com.intelliseven.spring_data_jpa_activity.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hearings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HearingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String caseName;
  @Column
  private LocalDate date;
  @Column
  private String status;
  @Column
  private String incident;
  @Column
  private String proceeding;

  public HearingEntity(String caseName, LocalDate date, String status, String incident, String proceeding) {
    this.caseName = caseName;
    this.date = date;
    this.status = status;
    this.incident = incident;
    this.proceeding = proceeding;
  }

}
