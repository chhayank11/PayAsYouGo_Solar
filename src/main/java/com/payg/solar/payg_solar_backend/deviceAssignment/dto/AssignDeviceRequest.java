package com.payg.solar.payg_solar_backend.deviceAssignment.dto;

import com.payg.solar.payg_solar_backend.deviceAssignment.entity.PaymentPlan;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignDeviceRequest {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "Device id is required")
    private Long deviceId;

    @NotNull(message = "Payment plan is required")
    private PaymentPlan paymentPlan;
}
