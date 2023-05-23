package com.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private UUID idCustomer;
    private Double amount;
    private Integer installments;
    private String paymentMethod;
    private List<OrderItemRequestDTO> items;
}
