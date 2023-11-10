package com.example.backend.mapper;

import com.example.backend.config.MapperConfig;
import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.example.backend.model.Order;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    OrderResponseDto toDto(Order service);

    Order toModel(OrderRequestDto requestDto);
}
