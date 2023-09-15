package com.brigelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String orderDate;
    @NotNull
    private Long price;
    @NotNull
    private Long quantity;
    @Pattern(regexp = "[A-Za-z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    private String address;
    @NotNull
    private boolean cancel;
}