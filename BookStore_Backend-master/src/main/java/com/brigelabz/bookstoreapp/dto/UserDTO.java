package com.brigelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {
    private long userId;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    private String firstName;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person last name Invalid")
    private String lastName;

    @NotEmpty(message = "Enter valid DOB")
    private String dateOfBirth;

    @Email(message = "Enter valid email ID")
    private String email;

    //@Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.[0-9])(?=.[-+(){}_!@#$%^&., ?]){8,}.$", message = "Enter Valid password")
    private String password;

    @Pattern(regexp = "(91 )[1-9]{1}[0-9]{9}", message = "Enter valid mobile number")
    private String phoneNumber;

    //@NotNull(message="Enter the valid KYC")
    private String kyc;

    @Pattern(regexp = "Admin|Normal User", message = "Enter valid role")
    private String role;
}