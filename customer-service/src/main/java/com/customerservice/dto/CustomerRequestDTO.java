package com.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String document;
    private Date birthDate;
    private CustomerAddressRequestDTO address;
}
