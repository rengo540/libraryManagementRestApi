package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.BorrowRequestRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRequestRepo extends JpaRepository<BorrowRequestRelation,Long> {
}
