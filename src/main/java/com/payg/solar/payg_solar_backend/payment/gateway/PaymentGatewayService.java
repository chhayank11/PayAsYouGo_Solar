package com.payg.solar.payg_solar_backend.payment.gateway;

import java.math.BigDecimal;

public interface PaymentGatewayService {

    String processPayment(Long assignmentId, BigDecimal amount);
}
