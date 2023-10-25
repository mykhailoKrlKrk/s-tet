package com.example.backend.mapper;

import com.example.backend.config.MapperConfig;
import com.example.backend.dto.master.MasterDto;
import com.example.backend.model.Master;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MasterMapper {

    MasterDto toDto(Master master);
}
