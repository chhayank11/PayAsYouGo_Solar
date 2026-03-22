package com.payg.solar.payg_solar_backend.payment.service;

import com.payg.solar.payg_solar_backend.common.exception.DuplicatePaymentException;
import com.payg.solar.payg_solar_backend.common.exception.ResourceNotFoundException;
import com.payg.solar.payg_solar_backend.device.entity.Device;
import com.payg.solar.payg_solar_backend.device.entity.DeviceStatus;
import com.payg.solar.payg_solar_backend.deviceAssignment.entity.DeviceAssignment;
import com.payg.solar.payg_solar_backend.deviceAssignment.repo.DeviceAssignmentRepository;
import com.payg.solar.payg_solar_backend.payment.dto.CreatePaymentRequest;
import com.payg.solar.payg_solar_backend.payment.dto.PaymentResponse;
import com.payg.solar.payg_solar_backend.payment.entity.Payment;
import com.payg.solar.payg_solar_backend.payment.entity.PaymentStatus;
import com.payg.solar.payg_solar_backend.payment.gateway.PaymentGatewayService;
import com.payg.solar.payg_solar_backend.payment.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private DeviceAssignmentRepository deviceAssignmentRepository;
    private PaymentRepository paymentRepository;

    private PaymentGatewayService paymentGatewayService;


    @Override
    public PaymentResponse makePayment(CreatePaymentRequest request) {

        // 1. Idempotency check (still first)
        Optional<Payment> existing =
                paymentRepository.findByIdempotencyKey(request.getIdempotencyKey());

        if (existing.isPresent()) {
            return mapToResponse(existing.get());
        }

        String txnRef;
        try {
            txnRef = paymentGatewayService.processPayment(
                    request.getAssignmentId(),
                    request.getAmount()
            );
        }catch (Exception ex){

            return handleFailedPayment(request);

        }

        return processSuccessfulPayment(request,txnRef);


    }

    private PaymentResponse handleFailedPayment(CreatePaymentRequest request) {
        DeviceAssignment assignment = deviceAssignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));

        Payment failedPayment = Payment.builder()
                .deviceAssignment(assignment)
                .customer(assignment.getCustomer())
                .device(assignment.getDevice())
                .amount(request.getAmount())
                .paymentDate(LocalDateTime.now())
                .status(PaymentStatus.FAILED)
                .idempotencyKey(request.getIdempotencyKey())
                .build();
        Payment saved = paymentRepository.save(failedPayment);
        return mapToResponse(saved);
    }

    @Transactional
    private PaymentResponse processSuccessfulPayment(CreatePaymentRequest request, String txnRef) {
        //fetch assignment
        DeviceAssignment assignment = deviceAssignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));


        //update device assignments.. amountPaid and lastPaymentDate
        assignment.setAmountPaid(assignment.getAmountPaid().add(request.getAmount()));
        assignment.setLastPaymentDate(LocalDateTime.now());

        //update device status
        Device device = assignment.getDevice();
        device.setStatus(DeviceStatus.ACTIVE);

        Payment payment = Payment.builder()
                .deviceAssignment(assignment)
                .customer(assignment.getCustomer())
                .device(device)
                .amount(request.getAmount())
                .paymentDate(LocalDateTime.now())
                .status(PaymentStatus.SUCCESS)
                .idempotencyKey(request.getIdempotencyKey())
                .transactionReference(txnRef)
                .build();

        try {

        Payment saved = paymentRepository.save(payment);
            return mapToResponse(saved);
        }
        catch (DataIntegrityViolationException ex){
            Payment duplicatePayment = paymentRepository
                    .findByIdempotencyKey(request.getIdempotencyKey())
                    .orElseThrow(() -> new DuplicatePaymentException("Payment conflict"));

            return mapToResponse(duplicatePayment);
        }
    }

    private static PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .assignmentId(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .transactionReference(payment.getTransactionReference())
                .build();
    }


}
