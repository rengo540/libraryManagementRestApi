package com.example.libraryMangementSystem.controllers;

import com.example.libraryMangementSystem.dtos.SimpleNameDto;
import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.services.AuthorService;
import com.example.libraryMangementSystem.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<ApiResponse> create(@RequestBody SimpleNameDto dto) {
        return ResponseEntity.ok(new ApiResponse("successfully created",publisherService.create(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(new ApiResponse("success",publisherService.getAll()));
    }
}
