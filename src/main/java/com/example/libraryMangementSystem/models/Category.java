package com.example.libraryMangementSystem.models;

import com.example.libraryMangementSystem.models.auditing.BaseEntityAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category extends BaseEntityAuditing {

    private String name ;


    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Book> book ;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Category> children = new ArrayList<>();

    public Category(String name) {
        this.name=name;
    }
}
