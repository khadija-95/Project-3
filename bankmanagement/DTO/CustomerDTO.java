package com.example.bankmanagement.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotNull
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;
}
