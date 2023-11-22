package com.example.backend.service.impl;

import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.model.Order;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.MasterService;
import com.example.backend.service.OrderService;
import com.example.backend.service.ServicesService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MasterService masterService;
    private final ServicesService servicesService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);

        order.setMaster(masterService.findById(requestDto.getMasterId()));
        Set<com.example.backend.model.Service> services =
                requestDto.getServicesId().stream()
                        .map(servicesService::findById)
                        .collect(Collectors.toSet());

        order.setServices(services);
        order.setOrderDate(LocalDateTime.now());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> getAll(Pageable pageable) {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find master by id: " + id));
        orderRepository.deleteById(id);
    }
}
