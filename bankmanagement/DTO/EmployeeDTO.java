package com.example.bankmanagement.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    @NotNull
    private String position;

    @NotNull
    @PositiveOrZero
    private Double salary;

}
