package com.payg.solar.payg_solar_backend.scheduler;

import com.payg.solar.payg_solar_backend.config.PaygProperties;
import com.payg.solar.payg_solar_backend.device.entity.Device;
import com.payg.solar.payg_solar_backend.device.entity.DeviceStatus;
import com.payg.solar.payg_solar_backend.deviceAssignment.entity.DeviceAssignment;
import com.payg.solar.payg_solar_backend.deviceAssignment.repo.DeviceAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeviceStatusScheduler {

    private final DeviceAssignmentRepository repo;

    private final PaygProperties paygProperties;

    @Transactional
    @Scheduled(fixedRate = 300000)
    public void checkAndUpdateDeviceStatus() {
        int page = 0;
        int size = 1000;

        LocalDateTime now = LocalDateTime.now();

        Page<DeviceAssignment> assignmentPage;
        do {

            assignmentPage =  repo.findActiveAssignments(PageRequest.of(page,size));

            for (DeviceAssignment assignment : assignmentPage.getContent()) {

                Device device = assignment.getDevice();

                BigDecimal remainingAmount = assignment.getTotalCost()
                        .subtract(assignment.getAmountPaid());

                if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
                    device.setStatus(DeviceStatus.ACTIVE);
                    continue;
                }

                int allowedDays = switch (assignment.getPaymentPlan()) {
                    case DAILY -> paygProperties.getDailyDays();
                    case WEEKLY -> paygProperties.getWeeklyDays();

                };

                if (assignment.getLastPaymentDate() == null ||
                        assignment.getLastPaymentDate().plusDays(allowedDays).isBefore(now)) {
                    device.setStatus(DeviceStatus.LOCKED);
                } else {
                    device.setStatus(DeviceStatus.ACTIVE);
                }

            }
            page++;
        }
        while (assignmentPage.hasNext());
    }
}
