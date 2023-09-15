package com.brigelabz.bookstoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="FirstName",nullable=false)
    private String firstName;
    @Column(name="LastName",nullable=false)
    private String lastName;
    @Column(name="DateOfBirth",nullable=false)
    private String dateOfBirth;
    @Column(name="Email",nullable=false)
    private String email;
    @Column(name="Password",nullable=false)
    private String password;
    @Column(name="PhoneNumber",nullable=false)
    private String phoneNumber;
    //@Column(name="KYC",nullable=false)
    private String kyc;
    @Column(name="Role",nullable=false)
    private String role;

    @OneToMany(mappedBy = "users")
    private List<Cart> carts;

    @OneToMany(mappedBy = "userOrder")
    private List<Order> orders;
}
