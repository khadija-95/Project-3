package com.example.bankmanagement.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotNull
    @Size(min = 4, max = 10)
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;
}
