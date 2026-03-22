package com.payg.solar.payg_solar_backend.payment.controller;

import com.payg.solar.payg_solar_backend.payment.dto.CreatePaymentRequest;
import com.payg.solar.payg_solar_backend.payment.dto.PaymentResponse;
import com.payg.solar.payg_solar_backend.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse makePayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {
        return paymentService.makePayment(request);
    }
}
