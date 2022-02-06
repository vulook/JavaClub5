package com.softserve.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

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

    @Column(name = "UserRoleID")
    private int userRoleId;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "RegDate")
    private Date regDate;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Cart> cartsById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Form> formsById;

    @ManyToOne
    @JoinColumn(name = "UserRoleID", referencedColumnName = "ID", nullable = false)
    private UserRole userroleByUserRoleId;


}
