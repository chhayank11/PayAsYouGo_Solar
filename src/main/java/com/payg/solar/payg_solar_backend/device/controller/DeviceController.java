package com.payg.solar.payg_solar_backend.device.controller;

import com.payg.solar.payg_solar_backend.device.dto.CreateDeviceRequest;
import com.payg.solar.payg_solar_backend.device.dto.DeviceResponse;
import com.payg.solar.payg_solar_backend.device.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService service;

    @PostMapping
    public DeviceResponse createDevice(@Valid @RequestBody CreateDeviceRequest req){
        return service.createDevice(req);
    }

    @GetMapping("/{id}")
    public DeviceResponse getDeviceById(@PathVariable Long id){
        return service.getDeviceById(id);
    }
}
