package com.customerservice.service;

import com.customerservice.entity.Customer;
import com.customerservice.entity.CustomerAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerService {

    Mono<Page<Customer>> getCustomers(Pageable pageable);

    Mono<Customer> getCustomerById(UUID id);

    Mono<Customer> create(Customer customer, CustomerAddress customerAddress);

    Mono<Customer> update(Long id, Customer customer);

    void delete(Long id);
}
