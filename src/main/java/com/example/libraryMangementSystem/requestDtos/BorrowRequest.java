package com.example.libraryMangementSystem.requestDtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRequest {
        private Long memberId;
        private Long bookId;
        private LocalDate desiredStartDate;

}
