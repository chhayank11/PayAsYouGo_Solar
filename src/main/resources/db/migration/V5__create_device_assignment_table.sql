CREATE TABLE device_assignments (
    id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL,
    device_id BIGINT NOT NULL,

    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_device FOREIGN KEY (device_id) REFERENCES devices(id)
);

CREATE INDEX idx_assignment_customer ON device_assignments(customer_id);
CREATE INDEX idx_assignment_device ON device_assignments(device_id);