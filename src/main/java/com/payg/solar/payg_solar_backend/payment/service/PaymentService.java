package com.payg.solar.payg_solar_backend.payment.service;

import com.payg.solar.payg_solar_backend.payment.dto.CreatePaymentRequest;
import com.payg.solar.payg_solar_backend.payment.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse makePayment(CreatePaymentRequest request);
}
