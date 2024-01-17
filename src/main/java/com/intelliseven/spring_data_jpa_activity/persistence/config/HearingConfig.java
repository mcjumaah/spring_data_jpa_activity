package com.intelliseven.spring_data_jpa_activity.persistence.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.Hearing;
import com.intelliseven.spring_data_jpa_activity.persistence.repository.HearingRepo;

@Configuration
public class HearingConfig {
  @Bean
  CommandLineRunner commandLineRunner(HearingRepo repository) {
    return args -> {
      Hearing usad = new Hearing("Juan vs. Pipol of da Pelepens", LocalDate.of(2000, Month.AUGUST, 18),
          "PROCEED", "Sample Incident ngaya", "Sample Proceeding kuno");
      Hearing darwa = new Hearing("Two-mas vs. Pipol of da Pelepens", LocalDate.of(2000, Month.AUGUST, 18),
          "PROCEED", "Sample Incident ngaya", "Sample Proceeding kuno");

      repository.saveAll(List.of(usad, darwa)); // No need to change this line
    };
  }
}
