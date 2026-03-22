package com.payg.solar.payg_solar_backend.payment.gateway;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MockPaymentGatewayServiceImpl implements PaymentGatewayService{
    @Override
    public String processPayment(Long assignmentId, BigDecimal amount) {
        return "TXN-"+ UUID.randomUUID();
    }
}
