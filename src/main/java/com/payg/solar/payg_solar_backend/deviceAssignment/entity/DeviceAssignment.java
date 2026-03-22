package com.payg.solar.payg_solar_backend.deviceAssignment.entity;


import com.payg.solar.payg_solar_backend.common.entity.BaseEntity;
import com.payg.solar.payg_solar_backend.customer.entity.Customer;
import com.payg.solar.payg_solar_backend.device.entity.Device;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceAssignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "last_payment_date")
    private LocalDateTime lastPaymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_plan", nullable = false)
    private PaymentPlan paymentPlan;
}