package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.CoverImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface ImageRepo extends JpaRepository<CoverImage,Long> {

    CoverImage findByFileName(String fileName);
}
