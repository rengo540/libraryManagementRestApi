package com.example.libraryMangementSystem.controllers;


import com.example.libraryMangementSystem.dtos.SimpleNameDto;
import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/add/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse> createMember(@RequestBody SimpleNameDto dto,
                                                    @PathVariable Long userId){
        return ResponseEntity.ok(new ApiResponse("created successfully",
                memberService.approveUser(userId,dto)));
    }
}
