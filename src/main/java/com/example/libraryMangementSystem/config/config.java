package com.example.libraryMangementSystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class config {
    public static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/java/com/example/libraryMangementSystem/uploads/";


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
