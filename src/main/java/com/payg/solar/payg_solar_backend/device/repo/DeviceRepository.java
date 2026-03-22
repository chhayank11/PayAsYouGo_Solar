package com.payg.solar.payg_solar_backend.device.repo;

import com.payg.solar.payg_solar_backend.device.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    Optional<Device> findBySerialNumber(String serialNumber);

    boolean existsBySerialNumber(String serialNumber);
}
