package com.example.libraryMangementSystem.models;

import com.example.libraryMangementSystem.models.auditing.BaseEntityAuditing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "imagePath" }))
public class CoverImage extends BaseEntityAuditing {

    private  String fileName ;
    private String fileType;
    private String imagePath;

    @OneToOne(mappedBy = "image")
    private Book book;


    public CoverImage(){}

}
