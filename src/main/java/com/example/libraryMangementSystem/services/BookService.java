package com.example.libraryMangementSystem.services;

import com.example.libraryMangementSystem.exceptions.AlreadyExistsException;
import com.example.libraryMangementSystem.exceptions.ResourceNotFoundException;
import com.example.libraryMangementSystem.models.Author;
import com.example.libraryMangementSystem.models.Book;
import com.example.libraryMangementSystem.repos.AuthorRepo;
import com.example.libraryMangementSystem.repos.BookRepo;
import com.example.libraryMangementSystem.repos.CategoryRepo;
import com.example.libraryMangementSystem.repos.PublisherRepo;
import com.example.libraryMangementSystem.requestDtos.BookRequest;
import com.example.libraryMangementSystem.responseDtos.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private PublisherRepo publisherRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Transactional
    public BookResponse addBook(BookRequest bookRequest) {

        if(isBookExist(bookRequest.getName())){
            throw new AlreadyExistsException("this book already exist");
        }
        return mapToResponse(createBook(bookRequest));
    }

    private boolean isBookExist(String name){
        return bookRepo.existsByName(name);
    }

    public Book createBook(BookRequest dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setLang(dto.getLanguage());
        book.setPublicationYear(dto.getPublicationDate());
        book.setISBN(dto.getIsbn());
        book.setEdition(dto.getEdition());
        book.setSummary(dto.getSummary());
        book.setAvailability(dto.getAvailability());

        book.setPublisher(publisherRepo.findById(dto.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found")));

        book.setCategory(categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));

        Set<Author> authors = authorRepo.findAllById(dto.getAuthorIds())
                .stream().collect(Collectors.toSet());
        book.setAuthors(authors);

        bookRepo.save(book);
        return book;
    }

    public List<BookResponse> getAllBooks() {
        return bookRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return book;
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    public BookResponse mapToResponse(Book book) {
        BookResponse dto = new BookResponse();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setLang(book.getLang());
        dto.setIsbn(book.getISBN());
        if(book.getImage() != null) {
            dto.setCoverImageUrl(book.getImage().getImagePath());
        }
        dto.setAvailability(book.getAvailability());
        dto.setPublisherName(book.getPublisher() != null ? book.getPublisher().getName() : null);
        dto.setCategoryName(book.getCategory() != null ? book.getCategory().getName() : null);
        dto.setAuthorNames(
                book.getAuthors().stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet())
        );
        return dto;
    }

}
