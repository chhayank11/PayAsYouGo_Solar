package com.payg.solar.payg_solar_backend.payment.repo;

import com.payg.solar.payg_solar_backend.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByAssignmentId(Long assignmentId);

    Optional<Payment> findByIdempotencyKey(String idempotencyKey);
}
