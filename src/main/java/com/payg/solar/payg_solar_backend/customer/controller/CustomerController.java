package com.payg.solar.payg_solar_backend.customer.controller;

import com.payg.solar.payg_solar_backend.customer.dto.CreateCustomerRequest;
import com.payg.solar.payg_solar_backend.customer.dto.CustomerResponse;
import com.payg.solar.payg_solar_backend.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest req){
        return service.createCustomer(req);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){
        return  service.getCustomerById(id);
    }
}
