package com.payg.solar.payg_solar_backend.device.dto;

import com.payg.solar.payg_solar_backend.device.entity.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceResponse {

    private Long id;
    private String serialNumber;
    private DeviceStatus status;
    private BigDecimal price;
}
