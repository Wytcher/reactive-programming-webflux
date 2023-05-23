package com.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CustomerAddressModel> addresses;
}
