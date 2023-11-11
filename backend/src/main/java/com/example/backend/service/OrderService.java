package com.example.backend.service;

import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);

    List<OrderResponseDto> getAll(Pageable pageable);

    void delete(Long id);
}
