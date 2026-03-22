ALTER TABLE device_assignments
ADD COLUMN total_cost NUMERIC(10,2) NOT NULL,
ADD COLUMN amount_paid NUMERIC(10,2) DEFAULT 0,
ADD COLUMN last_payment_date TIMESTAMP;