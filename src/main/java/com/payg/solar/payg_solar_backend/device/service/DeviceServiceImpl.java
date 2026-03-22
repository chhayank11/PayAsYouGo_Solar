package com.payg.solar.payg_solar_backend.device.service;

import com.payg.solar.payg_solar_backend.common.exception.ResourceNotFoundException;
import com.payg.solar.payg_solar_backend.device.dto.CreateDeviceRequest;
import com.payg.solar.payg_solar_backend.device.dto.DeviceResponse;
import com.payg.solar.payg_solar_backend.device.entity.Device;
import com.payg.solar.payg_solar_backend.device.entity.DeviceStatus;
import com.payg.solar.payg_solar_backend.device.repo.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository repo;

    @Override
    public DeviceResponse createDevice(CreateDeviceRequest req) {

        if(repo.existsBySerialNumber(req.getSerialNumber())){
            throw new RuntimeException("Device with this serial already exists");
        }

        Device device = Device.builder()
                .serialNumber(req.getSerialNumber())
                .price(req.getPrice())
                .status(DeviceStatus.INACTIVE)
                .build();


        Device saved = repo.save(device);

        return DeviceResponse.builder()
                .id(saved.getId())
                .serialNumber(saved.getSerialNumber())
                .status(saved.getStatus())
                .price(saved.getPrice())
                .build();
    }

    @Override
    public DeviceResponse getDeviceById(Long id) {


        Device device = repo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Device not found"));

        return DeviceResponse.builder()
                .id(device.getId())
                .serialNumber(device.getSerialNumber())
                .status(device.getStatus())
                .price(device.getPrice())
                .build();
    }
}
