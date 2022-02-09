package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "book")
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String bookName;

    private String genre;

    private long authorId;

    private int count;

    private int pageCount;

    private Integer ratings;

    //    @ManyToOne
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JoinColumn(name = "AuthorID", referencedColumnName = "ID")
//    private Author authorById;
//
//    @OneToMany(mappedBy = "bookByBookId")
//    private Collection<Cart> cartsById;
//
//    @OneToMany(mappedBy = "bookByBookId")
//    private Collection<Form> formsById;
//
//    @ManyToMany(mappedBy = "books",fetch = FetchType.EAGER)
//    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
//    private Set<Author> authors = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author mainAuthor;

    @ManyToMany
    @JoinTable(name = "co_author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> co_authors = new HashSet<>();
}
