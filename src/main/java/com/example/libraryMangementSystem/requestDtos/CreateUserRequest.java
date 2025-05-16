package com.example.libraryMangementSystem.requestDtos;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role; //to ensure each user has one role for now
}
