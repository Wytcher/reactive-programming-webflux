package com.orderservice.controller;

import com.orderservice.dto.OrderRequestDTO;
import com.orderservice.dto.OrderResponseDTO;
import com.orderservice.entity.Order;
import com.orderservice.service.OrderServiceImpl;
import com.orderservice.utils.MapperUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final MapperUtils mapper = new MapperUtils();
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<ResponseEntity<OrderResponseDTO>> create(@RequestBody OrderRequestDTO orderRequestDTO) {
        Mono<Order> orderMono = orderService.create(orderRequestDTO);
        return orderMono
                .map(order -> mapper.map(order, OrderResponseDTO.class))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
}
