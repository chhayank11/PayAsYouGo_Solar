package com.payg.solar.payg_solar_backend.customer.service;

import com.payg.solar.payg_solar_backend.common.exception.DuplicateResourceException;
import com.payg.solar.payg_solar_backend.common.exception.ResourceNotFoundException;
import com.payg.solar.payg_solar_backend.customer.dto.CreateCustomerRequest;
import com.payg.solar.payg_solar_backend.customer.entity.Customer;
import com.payg.solar.payg_solar_backend.customer.dto.CustomerResponse;
import com.payg.solar.payg_solar_backend.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository repository;


    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        // Business Validation
        if (repository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Customer with phone already exists");
        }

        // Map DTO → Entity
        Customer customer = Customer.builder()
                .name(request.getName().trim())
                .phone(request.getPhone().trim())
                .email(request.getEmail())
                .build();

        // Save
        Customer saved = repository.save(customer);

        // Map Entity → Response
        return CustomerResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .phone(saved.getPhone())
                .email(saved.getEmail())
                .build();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }
}
