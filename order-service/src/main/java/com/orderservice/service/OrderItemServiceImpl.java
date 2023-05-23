package com.orderservice.service;

import com.orderservice.entity.OrderItem;
import com.orderservice.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Mono<OrderItem> getOrderItems(Long idOrder) {
        return null;
    }

    @Override
    public Mono<OrderItem> create(OrderItem orderItem) {
        return null;
    }

    @Override
    public Mono<OrderItem> createAll(List<OrderItem> orderItems) {
        return null;
    }
}
