package com.example.libraryMangementSystem.controllers;

import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.services.BorrowingRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowingRelationService borrowingRelationService;

    @PostMapping("/{borrowingId}/return")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse> returnBook(@PathVariable Long borrowingId) {
        String message = borrowingRelationService.returnBook(borrowingId);
        return ResponseEntity.ok(new ApiResponse(message,null));
    }
}

