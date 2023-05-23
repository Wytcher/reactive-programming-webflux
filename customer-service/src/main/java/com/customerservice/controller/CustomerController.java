package com.customerservice.controller;

import com.customerservice.dto.CustomerRequestDTO;
import com.customerservice.dto.CustomerResponseDTO;
import com.customerservice.entity.Customer;
import com.customerservice.entity.CustomerAddress;
import com.customerservice.service.CustomerServiceImpl;
import com.customerservice.utils.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final MapperUtils mapper = new MapperUtils();

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Mono<ResponseEntity<Page<CustomerResponseDTO>>> getCustomers(Pageable pageable) {
        Mono<Page<Customer>> customersMono = customerService.getCustomers(pageable);
        return customersMono
                .map(customers -> mapper.mapEntityPageIntoDtoPage(customers, CustomerResponseDTO.class))
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerResponseDTO>> getCustomerById(@PathVariable UUID id) {
        Mono<Customer> customerMono = customerService.getCustomerById(id);
        return customerMono
                .map(customer -> mapper.map(customer, CustomerResponseDTO.class))
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerResponseDTO>> create(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customerEntity = mapper.map(customerRequestDTO, Customer.class);
        CustomerAddress customerAddressEntity = mapper.map(customerRequestDTO.getAddress(), CustomerAddress.class);
        Mono<Customer> customerMono = customerService.create(customerEntity, customerAddressEntity);
        return customerMono
                .map(customer -> mapper.map(customer, CustomerResponseDTO.class))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
}
