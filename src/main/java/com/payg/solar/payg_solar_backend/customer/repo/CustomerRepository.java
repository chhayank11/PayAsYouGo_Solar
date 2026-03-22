package com.payg.solar.payg_solar_backend.customer.repo;

import com.payg.solar.payg_solar_backend.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByPhone(String phone);

    boolean existsByPhone(String phone);
}
