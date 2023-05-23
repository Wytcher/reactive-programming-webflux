package com.customerservice.service;

import com.customerservice.entity.Customer;
import com.customerservice.entity.CustomerAddress;
import com.customerservice.exceptions.business.ObjectNotFoundException;
import com.customerservice.repository.CustomerAddressRepository;
import com.customerservice.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    @Override
    public Mono<Page<Customer>> getCustomers(Pageable pageable) {
        return Mono
                .fromCallable(() -> customerRepository.findAll(pageable))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Customer> getCustomerById(UUID id) {
        return Mono
                .fromCallable(() -> customerRepository.findCustomerById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(String.format("A customer for the given id %s was not found", id))))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Customer> create(Customer customer, CustomerAddress customerAddress) {
        return Mono
                .fromCallable(() -> customerRepository.save(customer))
                .doOnNext(createdCustomer -> {
                    ArrayList<CustomerAddress> addresses = new ArrayList<>();
                    customerAddress.setCustomer(createdCustomer);
                    addresses.add(customerAddressRepository.save(customerAddress));
                    createdCustomer.setAddresses(addresses);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Customer> update(Long id, Customer customer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
