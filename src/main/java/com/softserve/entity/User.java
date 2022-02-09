package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "User")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Age")
    private String age;


    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "RegDate")
    private Date regDate;
    @ManyToOne
    @JoinColumn(name = "UserRoleId")
    private UserRole role;

    @OneToMany(mappedBy = "CartUser")
    private List<Cart> cartList = new LinkedList<>();

    @OneToMany(mappedBy = "FormUser")
    private List<Form> formList = new LinkedList<>();
}
