package com.example.backend.controller;


import com.example.backend.dto.order.GetOrderSumRequestDto;
import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.example.backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Masters orders", description = "Endpoints for orders")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/sum")
    @Operation(summary = "Get services price",
            description = "Get total price based on selected services")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateSum(@RequestBody GetOrderSumRequestDto requestDto) {
        return orderService.getTotal(requestDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create order", description = "Create new order in the DB")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete order", description = "Delete order by specific id")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

}
