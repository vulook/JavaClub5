package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Author")
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @OneToMany(mappedBy = "mainAuthor")
    private Set<Book> books = new HashSet<>();

    @ManyToMany(mappedBy = "co_authors")
    private Set<Book> coAuthorBooks = new HashSet<>();

}
