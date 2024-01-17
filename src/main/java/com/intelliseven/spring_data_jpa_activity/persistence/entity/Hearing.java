package com.intelliseven.spring_data_jpa_activity.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hearings")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hearing {
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

  public Hearing(String caseName, LocalDate date, String status, String incident, String proceeding) {
    this.caseName = caseName;
    this.date = date;
    this.status = status;
    this.incident = incident;
    this.proceeding = proceeding;
  }

}
