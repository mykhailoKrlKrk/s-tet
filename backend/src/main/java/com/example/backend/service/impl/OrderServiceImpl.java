package com.example.backend.service.impl;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.dto.order.GetOrderSumRequestDto;
import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.example.backend.dto.service.ServiceDto;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.mapper.ServiceMapper;
import com.example.backend.model.Order;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.MasterService;
import com.example.backend.service.OrderService;
import com.example.backend.service.ServicesService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public BigDecimal getTotal(GetOrderSumRequestDto requestDto) {
        MasterDto master = masterService.getById(requestDto.getMasterId());
        List<Long> servicesId = requestDto.getServicesId();

        BigDecimal totalPrice = new BigDecimal(0);
        if (master.getQualification().equals("headMaster")) {
            for (Long id : servicesId) {
                ServiceDto byId = servicesService.findById(id);
                BigDecimal headMasterPrice = byId.getHeadMasterPrice();
                totalPrice = totalPrice.add(headMasterPrice);
            }
        } else {
            for (Long id : servicesId) {
                ServiceDto byId = servicesService.findById(id);
                BigDecimal masterPrice = byId.getMasterPrice();
                totalPrice = totalPrice.add(masterPrice);
            }
        }
        return totalPrice;
    }

}
