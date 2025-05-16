package com.example.libraryMangementSystem.services;

import com.example.libraryMangementSystem.dtos.SimpleNameDto;
import com.example.libraryMangementSystem.models.Author;
import com.example.libraryMangementSystem.repos.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public Author create(SimpleNameDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        return authorRepo.save(author);
    }

    public List<Author> getAll() {
        return authorRepo.findAll();
    }
}