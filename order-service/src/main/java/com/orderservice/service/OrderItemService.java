package com.orderservice.service;

import com.orderservice.entity.OrderItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderItemService {

    Mono<OrderItem> getOrderItems(Long idOrder);

    Mono<OrderItem> create(OrderItem orderItem);

    Mono<OrderItem> createAll(List<OrderItem> orderItems);
}
