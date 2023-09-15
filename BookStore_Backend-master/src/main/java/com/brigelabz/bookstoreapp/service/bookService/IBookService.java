package com.brigelabz.bookstoreapp.service.bookService;

import com.brigelabz.bookstoreapp.dto.BookDTO;
import com.brigelabz.bookstoreapp.entity.Book;

import javax.validation.constraints.Pattern;
import java.util.List;

public interface IBookService {


    List<Book> getAllBook();
    Book getBookByBookId(Long bookId);
    Book addBook(BookDTO bookDTO);
    Book updateBook(Long bookID, BookDTO bookDTO);
    void deleteBook(String token,Long bookId);

    Book changeBookQuantity(String token,Long bookId, Long quantity);
    Book changeBookPrice(Long bookId, Long price);
    List<Book> getBooksByOrderByPriceAsc();
    List<Book> getBooksByOrderByPriceDesc();

    List<Book> getBooksByOrderByBookIdAsc();
    List<Book> getBooksByOrderByBookIdDesc();

    List<Book> getBooksByAuthor(@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid") String author);

}
