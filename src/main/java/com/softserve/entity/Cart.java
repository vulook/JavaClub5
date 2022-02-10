package com.softserve.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
@EqualsAndHashCode(of = "id")
@Entity
@Data
@ToString(of = "id")
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
