package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {}