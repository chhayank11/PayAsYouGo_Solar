package com.payg.solar.payg_solar_backend.deviceAssignment.repo;

import com.payg.solar.payg_solar_backend.deviceAssignment.entity.DeviceAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceAssignmentRepository extends JpaRepository<DeviceAssignment,Long> {

    boolean existsByDeviceIdAndEndDateIsNull(Long deviceId);

    @Query(
            value = """
        SELECT da FROM DeviceAssignment da
        JOIN FETCH da.device
        WHERE da.endDate IS NULL
    """,
            countQuery = """
        SELECT COUNT(da) FROM DeviceAssignment da
        WHERE da.endDate IS NULL
    """
    )
    Page<DeviceAssignment> findActiveAssignments(Pageable pageable);
}
