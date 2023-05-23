package com.customerservice.service;

import com.customerservice.entity.CustomerAddress;
import com.customerservice.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressServiceImpl(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    @Override
    public Mono<CustomerAddress> getCustomerAddresses(Long idCustomer) {
        return null;
    }

    @Override
    public Mono<CustomerAddress> create(CustomerAddress customerAddress) {
        return null;
    }

    @Override
    public Mono<CustomerAddress> update(Long id, CustomerAddress customerAddress) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
