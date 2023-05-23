package com.orderservice.service;

import com.orderservice.dto.OrderRequestDTO;
import com.orderservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Page<Order>> getOrders(Pageable pageable);

    Mono<Order> getOrderById(Long id);

    Mono<Order> create(OrderRequestDTO orderRequestDTO);

    Mono<Order> update(Long id, Order order);

    void delete(Long id);
}
