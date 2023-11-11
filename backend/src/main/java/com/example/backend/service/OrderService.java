package com.example.backend.service;

import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);

    void delete(Long id);
}
