package com.payg.solar.payg_solar_backend.deviceAssignment.service;

import com.payg.solar.payg_solar_backend.deviceAssignment.dto.AssignDeviceRequest;
import com.payg.solar.payg_solar_backend.deviceAssignment.dto.DeviceAssignmentResponse;

public interface DeviceAssignmentService {

    DeviceAssignmentResponse assignDevice(AssignDeviceRequest request);
}
