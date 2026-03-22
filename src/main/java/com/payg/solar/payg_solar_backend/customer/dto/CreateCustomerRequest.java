package com.payg.solar.payg_solar_backend.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}%")
    private String phone;

    @Email
    private String email;
}
