package com.example.libraryMangementSystem.models;

import com.example.libraryMangementSystem.models.auditing.BaseEntity;
import com.example.libraryMangementSystem.models.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {


    @Enumerated(EnumType.ORDINAL)
    private Roles name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    public  Role(Roles name){
        this.name = name;
    }
}

