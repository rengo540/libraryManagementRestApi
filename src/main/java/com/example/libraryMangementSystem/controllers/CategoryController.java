package com.example.libraryMangementSystem.controllers;

import com.example.libraryMangementSystem.dtos.CategoryDto;
import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<ApiResponse> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse("successfully created",categoryService.create(categoryDto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(new ApiResponse("success",categoryService.getAll()));
    }
}