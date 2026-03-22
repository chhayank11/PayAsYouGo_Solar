package com.payg.solar.payg_solar_backend.deviceAssignment.controller;

import com.payg.solar.payg_solar_backend.deviceAssignment.dto.AssignDeviceRequest;
import com.payg.solar.payg_solar_backend.deviceAssignment.dto.DeviceAssignmentResponse;
import com.payg.solar.payg_solar_backend.deviceAssignment.service.DeviceAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceAssignmentController {

    private final DeviceAssignmentService service;

    @PostMapping("/{id}/assign")
    public DeviceAssignmentResponse assignDevice(@PathVariable Long id, @Valid @RequestBody AssignDeviceRequest request){

        request.setDeviceId(id);
        return service.assignDevice(request);
    }
}
