package com.example.backend.controller;

import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.example.backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Manage orders", description = "Endpoints for orders")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000",
        "http://s-tet.byethost12.com/"},
        methods = {
                RequestMethod.GET,
                RequestMethod.DELETE,
                RequestMethod.PUT,
                RequestMethod.POST
        })
@RequestMapping(value = "/orders")
public class OrderController {
    public static final Logger logger = LogManager.getLogger(OrderController.class);
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create order", description = "Create new order in the DB")
    public OrderResponseDto createOrder(@RequestBody @Valid OrderRequestDto requestDto) {
        logger.info("method createOrder is called with params: name - "
                + requestDto.getClientName() + ",services - " + requestDto.getServicesId()
                + ", master - " + requestDto.getMasterId());
        return orderService.createOrder(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all orders",
            description = "Get list of all available orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete order", description = "Delete order by specific id")
    public void delete(@PathVariable Long id) {
        logger.info("method delete order is called with id: " + id);
        orderService.delete(id);
    }

}

