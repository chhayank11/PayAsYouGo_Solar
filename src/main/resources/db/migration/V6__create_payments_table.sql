CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL,
    device_id BIGINT NOT NULL,
    assignment_id BIGINT NOT NULL,

    amount NUMERIC(10,2) NOT NULL,

    payment_date TIMESTAMP NOT NULL,

    status VARCHAR(20) NOT NULL, -- SUCCESS / FAILED / PENDING

    transaction_reference VARCHAR(100),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_payment_device FOREIGN KEY (device_id) REFERENCES devices(id),
    CONSTRAINT fk_payment_assignment FOREIGN KEY (assignment_id) REFERENCES device_assignments(id)
);
    CREATE INDEX idx_payment_customer ON payments(customer_id);
    CREATE INDEX idx_payment_device ON payments(device_id);
    CREATE INDEX idx_payment_assignment ON payments(assignment_id);