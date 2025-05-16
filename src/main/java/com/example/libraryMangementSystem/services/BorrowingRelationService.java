package com.example.libraryMangementSystem.services;

import com.example.libraryMangementSystem.models.Book;
import com.example.libraryMangementSystem.models.BorrowingRelation;
import com.example.libraryMangementSystem.models.Member;
import com.example.libraryMangementSystem.repos.BookRepo;
import com.example.libraryMangementSystem.repos.BorrowRelationRepo;
import com.example.libraryMangementSystem.repos.MemberRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingRelationService {

    private final BorrowRelationRepo borrowRepo;
    private final BookRepo bookRepo;
    private final MemberRepo memberRepo;

    @Transactional
    public String returnBook(Long borrowingId) {
        BorrowingRelation b = borrowRepo.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (b.getReturnedDate() != null) {
            return "Book already returned on " + b.getReturnedDate();
        }
        b.setReturnedDate(LocalDate.now());
        if(b.getReturnedDate().isAfter(b.getReturnedDueDate())){
            Member member =b.getMember();
            int reports = member.getNoOfReports() + 1;
            member.setNoOfReports(reports);
            memberRepo.save(member);
        }
        borrowRepo.save(b);

        // Increase availability
        Book book = b.getBook();
        book.setAvailability(book.getAvailability() + 1);
        bookRepo.save(book);

        return "Book returned successfully.";
    }
}
