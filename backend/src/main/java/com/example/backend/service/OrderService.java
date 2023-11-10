package com.example.backend.service;

import com.example.backend.dto.order.GetOrderSumRequestDto;
import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import java.math.BigDecimal;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);

    void delete(Long id);

    BigDecimal getTotal(GetOrderSumRequestDto requestDto);
}
