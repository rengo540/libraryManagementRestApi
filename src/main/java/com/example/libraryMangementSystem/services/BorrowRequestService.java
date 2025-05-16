package com.example.libraryMangementSystem.services;

import com.example.libraryMangementSystem.exceptions.ResourceNotFoundException;
import com.example.libraryMangementSystem.models.*;
import com.example.libraryMangementSystem.repos.*;
import com.example.libraryMangementSystem.requestDtos.BorrowRequest;
import com.example.libraryMangementSystem.security.UserAppDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowRequestService {

    private final BorrowRequestRepo borrowRequestRepo;
    private final MemberRepo memberRepo;
    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private final BorrowRelationRepo transactionRepo;

    public BorrowRequestRelation createBorrowRequest(BorrowRequest dto) {

        Member member = memberRepo.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepo.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BorrowRequestRelation request = new BorrowRequestRelation();
        request.setMember(member);
        request.setBook(book);
        request.setDesiredStartDate(dto.getDesiredStartDate());
        request.setStatus("PENDING");

        return borrowRequestRepo.save(request);
    }

    @Transactional
    public String approveRequest(Long requestId) {
        BorrowRequestRelation request = borrowRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Book book = request.getBook();

        if (book.getAvailability() > 0) {
            BorrowingRelation b = new BorrowingRelation();
            b.setBook(book);
            b.setMember(request.getMember());
            b.setBorrowingDate(LocalDate.now());
            b.setReturnedDueDate(LocalDate.now().plusDays(14)); // or configurable
            b.setReturnedDate(null);

            UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long adminId = userAppDetails.getId();
            User admin = userRepo.findById(adminId).orElseThrow(()-> new ResourceNotFoundException("admin not found"));
            b.setAdmin(admin);

            transactionRepo.save(b);

            book.setAvailability(book.getAvailability() - 1);
            bookRepo.save(book);

            request.setStatus("APPROVED");
            borrowRequestRepo.save(request);

            return "Request approved and book issued.";
        } else {
            // Check for soonest due date
            Optional<LocalDate> nextDue = transactionRepo
                    .findTopByBookAndReturnedDateIsNullOrderByReturnedDueDateAsc(book)
                    .map(BorrowingRelation::getReturnedDueDate);

            request.setStatus("REJECTED");
            borrowRequestRepo.save(request);

            return nextDue
                    .map(date -> "Book unavailable. Earliest return due: " + date)
                    .orElse("Book unavailable. No due date found.");
        }
    }
}
