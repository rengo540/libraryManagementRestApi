package com.example.libraryMangementSystem.controllers;

import com.example.libraryMangementSystem.models.Book;
import com.example.libraryMangementSystem.requestDtos.BookRequest;
import com.example.libraryMangementSystem.responseDtos.ApiResponse;
import com.example.libraryMangementSystem.responseDtos.BookResponse;
import com.example.libraryMangementSystem.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<ApiResponse> create(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.addBook(bookRequest);
        return ResponseEntity.ok(new ApiResponse("created successfully",bookResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse("success",bookResponses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookResponse bookResponse = bookService.mapToResponse(book);
        return ResponseEntity.ok(new ApiResponse("success",bookResponse));

    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse("success",null));
    }
}
