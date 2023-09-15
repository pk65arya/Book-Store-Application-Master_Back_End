package com.brigelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class BookDTO {

// the column value cannot be a null and the name of the column is Name
    @Column(nullable = false,name="Name")
    private String name;


    @Column(nullable = false,name="author")
    private String author;


    @Column(nullable = false,name="description")
    private String description;

    @Column(nullable = false,name="bookLogo")
    private String bookLogo;

    @Column(nullable = false,name="price")
    private Long price;

    @Column(nullable = false,name="quantity")
    private Long quantity;
}
