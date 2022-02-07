package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
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
    @ManyToMany
    @JoinTable(
            name = "coauthor",
            joinColumns = @JoinColumn(name = "BookID"),
            inverseJoinColumns = @JoinColumn(name = "AuthorID"))
    private Set<Book> booksById;


}
