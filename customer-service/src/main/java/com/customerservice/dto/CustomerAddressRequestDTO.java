package com.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressRequestDTO {

    private String street;
    private String district;
    private String zipCode;
    private String city;
    private String number;

}
