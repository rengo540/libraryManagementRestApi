package com.example.libraryMangementSystem.services;

import com.example.libraryMangementSystem.dtos.SimpleNameDto;
import com.example.libraryMangementSystem.models.Publisher;
import com.example.libraryMangementSystem.repos.PublisherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepo publisherRepo;

    public Publisher create(SimpleNameDto dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());
        return publisherRepo.save(publisher);
    }

    public List<Publisher> getAll() {
        return publisherRepo.findAll();
    }
}
