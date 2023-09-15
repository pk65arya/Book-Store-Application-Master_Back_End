package com.brigelabz.bookstoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    @Email
    private String email;

    @Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.[0-9])(?=.[-+(){}_!@#$%^&., ?]){8,}.$", message = "Enter Valid password")
    private String password;

    @NotNull
    private String role;

    @NotNull
    private Long otp;

    /*
        @OneToOne(mappedBy="userList")
        private Cart cart;

        @OneToMany(mappedBy="user")
        private List<Order> orderList;

    */
}
