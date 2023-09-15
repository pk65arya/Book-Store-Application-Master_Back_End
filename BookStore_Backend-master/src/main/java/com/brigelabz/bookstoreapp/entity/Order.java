package com.brigelabz.bookstoreapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="orderTable")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderDate = String.valueOf(LocalDate.now());
    @NotNull
    private Long price;
    @NotNull
    private Long quantity;
    @Pattern(regexp = "[A-Za-z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    private String address;

    @JsonIgnore
    @ManyToOne
    private User userOrder;

    @JsonIgnore
    @ManyToMany
    private List<Book> books;

    public void addBookToOrder(Book bookByBookId) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(bookByBookId);
    }

    @NotNull
    private boolean cancel;
}
