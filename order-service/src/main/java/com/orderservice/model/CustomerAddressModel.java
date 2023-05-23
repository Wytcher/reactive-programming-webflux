package com.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressModel {

    private UUID id;
    private String street;
    private String district;
    private String zipCode;
    private String city;
    private String number;
}
