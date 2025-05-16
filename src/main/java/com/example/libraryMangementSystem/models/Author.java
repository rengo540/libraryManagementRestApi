package com.example.libraryMangementSystem.models;

import com.example.libraryMangementSystem.models.auditing.BaseEntityAuditing;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Author extends BaseEntityAuditing {

    private String name;


    @ManyToMany(mappedBy = "authors")
    Set<Book> books;
}
