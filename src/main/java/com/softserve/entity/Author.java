package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

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
    @Column(nullable = false)
    @Id
    private long id;

    private String firstName;

    private String lastName;

    //
//    @ManyToMany
//    @JoinTable(
//            name = "coauthor",
//            joinColumns = @JoinColumn(name = "BookID"),
//            inverseJoinColumns = @JoinColumn(name = "AuthorID"))
//    Collection<Book> booksById;
//
//(mappedBy = "authorByAuthorId")
//    private Collection<Book> booksById;
//    @ManyToMany
//    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
//    @JoinTable(
//            name = "coauthor",
//            joinColumns = @JoinColumn(name = "BookID", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "AuthorID", referencedColumnName = "id"))
//    private Set<Book> books = new java.util.LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "authorById", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<Book> authorById = new java.util.ArrayList<>();
    @OneToMany(mappedBy = "mainAuthor")
    private Set<Book> books = new HashSet<>();

    @ManyToMany(mappedBy = "co_authors")
    private Set<Book> coAuthorBooks = new HashSet<>();
}
