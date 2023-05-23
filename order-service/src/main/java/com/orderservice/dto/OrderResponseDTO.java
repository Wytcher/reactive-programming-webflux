package com.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private UUID id;
    private UUID idCustomer;
    private Double amount;
    private Integer installments;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
