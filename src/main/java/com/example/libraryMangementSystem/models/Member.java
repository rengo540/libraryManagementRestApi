package com.example.libraryMangementSystem.models;

import com.example.libraryMangementSystem.models.auditing.BaseEntityAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntityAuditing {

    private String nickname;
    private boolean isBlocked; //true if noOfReports reaches to 5
    private int noOfReports; //+1 if returnDate - returnDueDate = 1

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id",unique = true)
    private User user;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    Set<BorrowingRelation> borrowingRelations;
}
