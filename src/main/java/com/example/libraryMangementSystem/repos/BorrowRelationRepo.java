package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Book;
import com.example.libraryMangementSystem.models.BorrowingRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRelationRepo extends JpaRepository<BorrowingRelation,Long> {

    Optional<BorrowingRelation> findTopByBookAndReturnedDateIsNullOrderByReturnedDueDateAsc(Book book);

}
