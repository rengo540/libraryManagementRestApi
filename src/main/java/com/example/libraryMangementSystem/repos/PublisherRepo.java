package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {}