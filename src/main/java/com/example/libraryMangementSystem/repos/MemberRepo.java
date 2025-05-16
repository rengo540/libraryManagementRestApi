package com.example.libraryMangementSystem.repos;

import com.example.libraryMangementSystem.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Long> {
}
