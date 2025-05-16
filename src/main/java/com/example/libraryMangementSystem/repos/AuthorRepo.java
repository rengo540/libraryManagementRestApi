package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {}