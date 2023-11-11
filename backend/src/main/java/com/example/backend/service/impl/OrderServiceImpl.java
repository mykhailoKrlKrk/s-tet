package com.example.backend.service.impl;

import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.mapper.ServiceMapper;
import com.example.backend.model.Order;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.MasterService;
import com.example.backend.service.OrderService;
import com.example.backend.service.ServicesService;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MasterService masterService;
    private final ServicesService servicesService;
    private final ServiceMapper serviceMapper;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setClientName(requestDto.getClientName());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderTotal(requestDto.getOrderTotal());
        order.setMaster(masterService.findById(requestDto.getMasterId()));
        order.setComment(requestDto.getComment());
        Set<com.example.backend.model.Service> services =
                requestDto.getServicesId().stream()
                        .map(servicesService::findById)
                        .map(serviceMapper::toModel)
                        .collect(Collectors.toSet());
        order.setServices(services);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
