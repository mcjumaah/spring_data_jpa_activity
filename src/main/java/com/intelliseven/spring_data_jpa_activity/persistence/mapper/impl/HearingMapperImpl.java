package com.intelliseven.spring_data_jpa_activity.persistence.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.intelliseven.spring_data_jpa_activity.persistence.entity.HearingEntity;
import com.intelliseven.spring_data_jpa_activity.persistence.mapper.Mapper;
import com.intelliseven.spring_data_jpa_activity.presentation.dto.HearingDto;

@Component
public class HearingMapperImpl implements Mapper<HearingEntity, HearingDto> {

  private ModelMapper modelMapper;

  public HearingMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public HearingDto mapTo(HearingEntity hearingEntity) {
    return modelMapper.map(hearingEntity, HearingDto.class);
  }

  @Override
  public HearingEntity mapFrom(HearingDto hearingDto) {
    return modelMapper.map(hearingDto, HearingEntity.class);
  }

}
