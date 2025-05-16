package com.example.libraryMangementSystem.services;

import com.example.libraryMangementSystem.dtos.CategoryDto;
import com.example.libraryMangementSystem.models.Category;
import com.example.libraryMangementSystem.repos.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category create(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());

        if (dto.getParentId() != null) {
            Category parent = categoryRepo.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));

            //Prevent recursive cycle
            if (isCyclic(parent, category)) {
                throw new IllegalArgumentException("Recursive parent-child relation detected");
            }

            category.setParent(parent);
        }

        return categoryRepo.save(category);
    }

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    private boolean isCyclic(Category potentialParent, Category child) {
        Category current = potentialParent;
        while (current != null) {
            if (current.getId() != null && current.getId().equals(child.getId())) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }
}