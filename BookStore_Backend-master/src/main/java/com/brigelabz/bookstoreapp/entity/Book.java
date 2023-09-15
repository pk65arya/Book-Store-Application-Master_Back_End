package com.brigelabz.bookstoreapp.entity;

import com.brigelabz.bookstoreapp.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data //for generating getter setter and toString method
@NoArgsConstructor  //for generating no argument constructor of Book
@Entity //this is used to mark Book class as a entity
@Table(name = "Bookdata") //this is used for creating table of name what we want
public class Book {
//this is used for indicates bookId as primary key
    @Id
    //automatically generate unique value of primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    // this is for validating name pattern
    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person name Invalid")
    private String name;

    private String author;

    private String description;

    private String bookLogo;

    private Long price;
//this is used for many to many relationship between books and order like One book have many order and one order have contains many book
    @ManyToMany(mappedBy = "books")
    private List<Order> order;

    @JsonIgnore //for ignoring this variable
    @ManyToMany(mappedBy = "books",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Cart> carts;

    private Long quantity;

    public Book(BookDTO bookDTO) {
    }
}
