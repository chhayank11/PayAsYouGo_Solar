package com.payg.solar.payg_solar_backend.deviceAssignment.dto;

import com.payg.solar.payg_solar_backend.deviceAssignment.entity.PaymentPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceAssignmentResponse {

    private Long assignmentId;
    private Long customerId;
    private Long deviceId;

    private BigDecimal totalCost;
    private BigDecimal amountPaid;
    private LocalDateTime lastPaymentDate;
    private PaymentPlan paymentPlan;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
