package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Cart")
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "UserID")
    private long userId;

    @Column(name = "BookID")
    private long bookId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    private User userByUserId;

    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID")
    private Book bookByBookId;


}
