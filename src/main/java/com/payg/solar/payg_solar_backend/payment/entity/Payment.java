package com.payg.solar.payg_solar_backend.payment.entity;

import com.payg.solar.payg_solar_backend.common.entity.BaseEntity;
import com.payg.solar.payg_solar_backend.customer.entity.Customer;
import com.payg.solar.payg_solar_backend.device.entity.Device;
import com.payg.solar.payg_solar_backend.deviceAssignment.entity.DeviceAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assessment_id")
    private DeviceAssignment deviceAssignment;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "idempotency_key", nullable = false, unique = true)
    private String idempotencyKey;

}
