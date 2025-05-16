package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<Book> findAll(Pageable pageable);

    List<Book> findByCategoryName(String category);


    List<Book> findByName(String name);

    boolean existsByName(String name);
}

