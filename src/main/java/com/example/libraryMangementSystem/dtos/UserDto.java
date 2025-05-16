package com.example.libraryMangementSystem.dtos;

import com.example.libraryMangementSystem.models.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
