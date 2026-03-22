package com.payg.solar.payg_solar_backend.deviceAssignment.service;

import com.payg.solar.payg_solar_backend.common.exception.BusinessException;
import com.payg.solar.payg_solar_backend.common.exception.ResourceNotFoundException;
import com.payg.solar.payg_solar_backend.customer.entity.Customer;
import com.payg.solar.payg_solar_backend.customer.repo.CustomerRepository;
import com.payg.solar.payg_solar_backend.customer.service.CustomerService;
import com.payg.solar.payg_solar_backend.device.entity.Device;
import com.payg.solar.payg_solar_backend.device.entity.DeviceStatus;
import com.payg.solar.payg_solar_backend.device.repo.DeviceRepository;
import com.payg.solar.payg_solar_backend.device.service.DeviceService;
import com.payg.solar.payg_solar_backend.deviceAssignment.dto.AssignDeviceRequest;
import com.payg.solar.payg_solar_backend.deviceAssignment.dto.DeviceAssignmentResponse;
import com.payg.solar.payg_solar_backend.deviceAssignment.entity.DeviceAssignment;
import com.payg.solar.payg_solar_backend.deviceAssignment.repo.DeviceAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeviceAssignmentServiceImpl implements DeviceAssignmentService {

    private final DeviceAssignmentRepository deviceAssignmentRepository;

    private final CustomerRepository customerRepository;

    private final DeviceRepository deviceRepository;

    @Transactional
    @Override
    public DeviceAssignmentResponse assignDevice(AssignDeviceRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Device device = deviceRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));


        if (deviceAssignmentRepository.existsByDeviceIdAndEndDateIsNull(request.getDeviceId())) {
            throw new BusinessException("Device already assigned");
        }

        DeviceAssignment assignment = DeviceAssignment.builder()
                .customer(customer)
                .device(device)
                .startDate(LocalDateTime.now())
                .endDate(null)
                .totalCost(device.getPrice())
                .amountPaid(BigDecimal.ZERO)
                .paymentPlan(request.getPaymentPlan())
                .build();

        DeviceAssignment saved = deviceAssignmentRepository.save(assignment);

        device.setStatus(DeviceStatus.LOCKED);
        deviceRepository.save(device);

        return DeviceAssignmentResponse.builder()
                .assignmentId(saved.getId())
                .customerId(customer.getId())
                .deviceId(device.getId())
                .totalCost(saved.getTotalCost())
                .amountPaid(saved.getAmountPaid())
                .lastPaymentDate(saved.getLastPaymentDate())
                .paymentPlan(saved.getPaymentPlan())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .build();


    }
}
