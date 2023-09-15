package com.brigelabz.bookstoreapp.service.bookService;

import com.brigelabz.bookstoreapp.dto.BookDTO;
import com.brigelabz.bookstoreapp.entity.Book;
import com.brigelabz.bookstoreapp.repository.BookRepository;
import com.brigelabz.bookstoreapp.service.UserLogin.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //this indicates that this class provides some business functionlitis,some logical things
@Slf4j // this is for generating logs
public class BookServiceImpl implements IBookService{

    @Autowired // for injecting dependencies
    private BookRepository bookRepository;

    @Autowired
    //The goal of ModelMapper is to make object mapping easy by automatically determining how one object model maps to another.
    private ModelMapper modelMapper;
    @Override
    public List<Book> getAllBook() {

        return bookRepository.findAll();
    }

    @Override
    public Book getBookByBookId(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        log.debug("Book Data: "+ book.toString());
        return bookRepository.save(book);

    }

    @Override
    public Book updateBook(Long bookId, BookDTO bookDTO) {
        Book book = this.getBookByBookId(bookId);
        modelMapper.map(bookDTO, book);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String token,Long bookId) {
        String receivedSubject = UserLoginServiceImpl.findSubByDecodeToken(token);
        String actualSubject = UserLoginServiceImpl.findSubByDecodeToken(UserLoginServiceImpl.TOKEN);
        System.out.println(actualSubject+ "    "+ receivedSubject);
        if(UserLoginServiceImpl.TOKEN.equals(token)) {
            Book book = this.getBookByBookId(bookId);
            bookRepository.delete(book);
        }
        else
            System.out.println("invalid");

    }

    @Override
    public Book changeBookQuantity(String token,Long bookId, Long quantity) {
        String receivedSubject = UserLoginServiceImpl.findSubByDecodeToken(token);
        String actualSubject = UserLoginServiceImpl.findSubByDecodeToken(UserLoginServiceImpl.TOKEN);
        System.out.println(actualSubject+ "    "+ receivedSubject);
        if(UserLoginServiceImpl.TOKEN.equals(token))
            System.out.println("valid"+ UserLoginServiceImpl.TOKEN);
        else

            System.out.println("invalid");
        return null;
    }

    @Override
    public Book changeBookPrice(Long bookId, Long price) {
        return null;
    }

    @Override
    public List<Book> getBooksByOrderByPriceAsc() {
        return bookRepository.getBooksByOrderByPriceAsc();
    }

    @Override
    public List<Book> getBooksByOrderByPriceDesc() {
        return bookRepository.getBooksByOrderByPriceDesc();
    }

    @Override
    public List<Book> getBooksByOrderByBookIdAsc() {
        return bookRepository.getBooksByOrderByBookIdAsc();
    }

    @Override
    public List<Book> getBooksByOrderByBookIdDesc() {
        return bookRepository.getBooksByOrderByBookIdDesc();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

}
