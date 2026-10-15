package com.Sailaja.novapay.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data

public class LoginRequest {
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Enter a valid Email ")
    private String email;
    @NotBlank(message = "Enter your password ")
    private String password;

    }

