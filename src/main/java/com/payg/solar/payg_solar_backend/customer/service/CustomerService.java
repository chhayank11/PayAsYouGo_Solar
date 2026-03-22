package com.payg.solar.payg_solar_backend.customer.service;

import com.payg.solar.payg_solar_backend.customer.dto.CreateCustomerRequest;
import com.payg.solar.payg_solar_backend.customer.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerRequest request);

    CustomerResponse getCustomerById(Long id);
}
