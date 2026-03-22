package com.payg.solar.payg_solar_backend.device.service;

import com.payg.solar.payg_solar_backend.device.dto.CreateDeviceRequest;
import com.payg.solar.payg_solar_backend.device.dto.DeviceResponse;

public interface DeviceService {

    DeviceResponse createDevice(CreateDeviceRequest req);

    DeviceResponse getDeviceById(Long id);
}
