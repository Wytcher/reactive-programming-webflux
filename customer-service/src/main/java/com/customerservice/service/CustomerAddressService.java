package com.customerservice.service;

import com.customerservice.entity.CustomerAddress;
import reactor.core.publisher.Mono;

public interface CustomerAddressService {

    Mono<CustomerAddress> getCustomerAddresses(Long idCustomer);

    Mono<CustomerAddress> create(CustomerAddress customerAddress);

    Mono<CustomerAddress> update(Long id, CustomerAddress customerAddress);

    void delete(Long id);
}
