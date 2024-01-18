package com.intelliseven.spring_data_jpa_activity.persistence.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
