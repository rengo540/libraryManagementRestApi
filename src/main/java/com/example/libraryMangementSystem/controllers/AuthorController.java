package com.example.libraryMangementSystem.controllers;

import com.example.libraryMangementSystem.dtos.SimpleNameDto;
import com.example.libraryMangementSystem.models.Author;
import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<ApiResponse> create(@RequestBody SimpleNameDto dto) {
        return ResponseEntity.ok(new ApiResponse("successfully created",authorService.create(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(new ApiResponse("success",authorService.getAll()));
    }
}