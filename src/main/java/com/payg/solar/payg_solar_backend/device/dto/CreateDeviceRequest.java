package com.payg.solar.payg_solar_backend.device.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDeviceRequest {

    @NotBlank(message = "Serial number is required")
    private String serialNumber;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
}
