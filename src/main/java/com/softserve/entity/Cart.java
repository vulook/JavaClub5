package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
@EqualsAndHashCode(of = "id")
@Entity
@Data
@ToString
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
