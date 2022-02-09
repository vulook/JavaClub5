package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "BookName")
    private String bookName;

    @Column(name = "Genre")
    private String genre;

//    @Column(name = "AuthorID")
//    private long authorId;

    @Column(name = "Count")
    private int count;

    @Column(name = "PageCount")
    private int pageCount;

    @Column(name = "Ratings")
    private Integer ratings;

    @ManyToOne
    @JoinColumn(name = "AuthorID")
    private Author mainAuthor;

    @ManyToMany
    @JoinTable(name = "coauthor",
            joinColumns = @JoinColumn(name = "BookID"),
            inverseJoinColumns = @JoinColumn(name = "AuthorID")
    )
    private Set<Author> co_authors = new HashSet<>();
    @OneToMany(mappedBy = "CartBook")
    private List<Cart> cartList = new LinkedList<>();

    @OneToMany(mappedBy = "FormBook")
    private List<Form> formList = new LinkedList<>();
}
