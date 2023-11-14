package com.example.backend.mapper;

import com.example.backend.config.MapperConfig;
import com.example.backend.dto.service.ServiceDto;
import com.example.backend.dto.service.ServiceRequestDto;
import com.example.backend.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ServiceMapper {
    @Mapping(target = "masterPrice", source = "service.masterPrice")
    @Mapping(target = "headMasterPrice", source = "service.headMasterPrice")
    ServiceDto toDto(Service service);

    Service toModelDto(ServiceDto serviceDto);

    Service toModel(ServiceRequestDto requestDto);
}
