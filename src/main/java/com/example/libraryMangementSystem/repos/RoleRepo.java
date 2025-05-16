package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Role;
import com.example.libraryMangementSystem.models.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Roles name);
}
