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

    private byte action;
    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book CartBook;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User CartUser;


}
