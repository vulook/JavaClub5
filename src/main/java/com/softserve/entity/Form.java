package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "Form")
public class Form {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

//    @Column(name = "UserID")
//    private Long userId;
//
//    @Column(name = "BookID")
//    private long bookId;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "ReturnDate")
    private Date returnDate;

    @Column(name = "BookReturned")
    private Date bookReturned;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "BookID")
    private Book FormBook;

    @ManyToOne
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "UserID")
    private User FormUser;
}
