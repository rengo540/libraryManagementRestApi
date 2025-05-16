package com.example.libraryMangementSystem.dtos;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private Long parentId; // nullable
}