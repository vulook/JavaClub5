package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

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

    @Column(name = "AuthorID")
    private long authorId;

    @Column(name = "Count")
    private int count;

    @Column(name = "PageCount")
    private int pageCount;

    @Column(name = "Ratings")
    private Integer ratings;

    @ManyToOne
    @JoinColumn(name = "AuthorID", referencedColumnName = "ID")
    private Author authorByAuthorId;

    @OneToMany(mappedBy = "bookByBookId")
    private Collection<Cart> cartsById;


    @OneToMany(mappedBy = "bookByBookId")
    private Collection<Form> formsById;


}
