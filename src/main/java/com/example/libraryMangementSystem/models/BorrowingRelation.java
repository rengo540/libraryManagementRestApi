package com.example.libraryMangementSystem.models;

import com.example.libraryMangementSystem.models.auditing.BaseEntityAuditing;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BorrowingRelation extends BaseEntityAuditing {


    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    User admin;

    @CreationTimestamp
    private LocalDate borrowingDate;
    private LocalDate returnedDueDate;
    private LocalDate returnedDate;
    private int lateDays;

}