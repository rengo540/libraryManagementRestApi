package com.example.libraryMangementSystem.responseDtos;

import lombok.Data;

import java.util.Set;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private String lang;
    private String isbn;
    private String coverImageUrl;
    private int availability;
    private String publisherName;
    private String categoryName;
    private Set<String> authorNames;
}
