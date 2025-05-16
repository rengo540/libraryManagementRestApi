package com.example.libraryMangementSystem.controllers;

import com.example.libraryMangementSystem.models.BorrowRequestRelation;
import com.example.libraryMangementSystem.requestDtos.BorrowRequest;
import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.services.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow-requests")
@RequiredArgsConstructor
public class BorrowRequestController {

    private final BorrowRequestService borrowRequestService;

    @PostMapping
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<ApiResponse> create(@RequestBody BorrowRequest dto) {
        BorrowRequestRelation req = borrowRequestService.createBorrowRequest(dto);
        return ResponseEntity.ok(new ApiResponse("Request submitted with ID: " , req.getId()));
    }

    @PostMapping("/{borrowRequestId}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> approve(@PathVariable Long borrowRequestId) {
        String result = borrowRequestService.approveRequest(borrowRequestId);
        return ResponseEntity.ok(new ApiResponse(result,null));
    }
}
