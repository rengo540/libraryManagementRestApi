package com.example.libraryMangementSystem.requestDtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Data
public class BookRequest {
    private String name;
    private String language;
    private String publicationDate;
    private String isbn;
    private String coverImageUrl;
    private String edition;
    private String summary;
    private int availability;
    private Long publisherId;
    private Long categoryId;
    private Set<Long> authorIds;
}
