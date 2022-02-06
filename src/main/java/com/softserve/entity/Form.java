package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Form")
public class Form {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "BookID")
    private long bookId;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "ReturnDate")
    private Date returnDate;

    @Column(name = "BookReturned")
    private Date bookReturned;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    private User userByUserId;

    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID")
    private Book bookByBookId;


}
