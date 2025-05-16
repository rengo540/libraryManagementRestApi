package com.example.libraryMangementSystem.requestDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Invalid credentials")
    private String email;

    @NotBlank(message = "Invalid credentials")
    private String password;
}