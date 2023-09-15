package com.brigelabz.bookstoreapp.controller.CartController;

import com.brigelabz.bookstoreapp.dto.BookDTO;
import com.brigelabz.bookstoreapp.dto.CartDTO;
import com.brigelabz.bookstoreapp.dto.ResponseDTO;
import com.brigelabz.bookstoreapp.entity.Book;
import com.brigelabz.bookstoreapp.entity.Cart;
import com.brigelabz.bookstoreapp.service.cartService.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookstorecart")
@CrossOrigin
public class cartController {
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAllCartItems() {
        List<Cart> allCartItems = cartService.getAllCartItems();
//        List<Book> bookList = doArrayList(allCartItems);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfull", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable("userId") Long userId) {
        List<Cart> cartData = cartService.getBookStoreDataByUserId(userId);
        ResponseDTO respDTO = new ResponseDTO("Get Call By Id Success", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCartData(@RequestBody CartDTO cartDTO) {
        Cart cart = cartService.addDataToCart(cartDTO);
        ResponseDTO respDTO = new ResponseDTO(" Cart data created", cart);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    // addBooksToCartByCartID
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ResponseDTO> updateBookStoreData(@PathVariable("cartId") Long cartId,@RequestParam Long quatity) {
        Cart cartData = cartService.updateCart(cartId, quatity);
        ResponseDTO respDTO = new ResponseDTO("updated adressBook data", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }


    // need to work
    @PutMapping("/update/addBooksToCartByCartID/{cartId}")
    public ResponseEntity<ResponseDTO> addBooksToCartByCartID(@PathVariable("cartId") Long cartId,@RequestParam Long bookId) {
        Cart cartData = cartService.addBooksToCartByCartID(cartId, bookId);
        ResponseDTO respDTO = new ResponseDTO("Book updated successfully in cart : ", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBookStoreData(@PathVariable("cartId") Long cartId) {
        cartService.removeCart(cartId);
        ResponseDTO respDTO = new ResponseDTO("deleted sucessful ", "Deleted Id" + cartId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Get cart List for user
    @GetMapping(value = {"/get-All-Cart-Items-User"})
    public ResponseEntity<ResponseDTO> getAllCartItemsUser(@RequestParam String token) {
        List<Cart> allCartItems = cartService.getAllCartItemsUser(token);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfully", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Get cart List for user
    @GetMapping(value = {"/get-All-Carts-By-User-Id"})
    public ResponseEntity<ResponseDTO> findCartsByUsers_Id(@RequestParam Long userId) {
        List<Cart> allCartItems = cartService.findCartsByUsers_Id(userId); // print if you want to see Cart toString
        List<Book> bookList = doArrayList(allCartItems);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfully", bookList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/removeall/{token}")
    ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("token") String token){
        cartService.removeAllCarts(token);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Delete call success",""), HttpStatus.OK);
    }

    // get Book Store Data By User Id
    @GetMapping(value = {"/getBookStoreDataByUserId"})
    public ResponseEntity<ResponseDTO> getBookStoreDataByUserId(@RequestParam Long userId) {
        List<Cart> allCartItems = cartService.getBookStoreDataByUserId(userId);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfully", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // doing list to array list to get particular Data format from DataBase
    List<Book> doArrayList(List<Cart> arrayList){

        ArrayList<Book> bookArrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            List<Book> bookList = arrayList.get(i).getBooks();
            for (int j = 0; j < bookList.size(); j++) {
                Book book = bookList.get(j);
                bookArrayList.add(book);
            }
        }
        return bookArrayList;
    }


//    @GetMapping("get/{token}")
//    List<Cart> findAllCartsByUser(@PathVariable("token") String token){
//        List<Cart> carts = cartService.getAllCartItems(token);
//        return carts;
//    }

    @GetMapping(value = {"/getTokenByEmail/{token}"})
    public ResponseEntity<ResponseDTO> getTokenByCart(@PathVariable String token){
        List<Book> cartId = cartService.getTokenByEmail(token);
        ResponseDTO respDTO = new ResponseDTO("Get token Successfully", cartId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping("/deleteBookByBookId/{books_book_id}")
    public ResponseEntity<ResponseDTO> deleteBookByBookId(@PathVariable("books_book_id") Long books_book_id) {
        cartService.deleteBookByBook_Id(books_book_id);
        ResponseDTO respDTO = new ResponseDTO("deleted successful ", "Deleted Id" + books_book_id);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping(value = {"/getUserIdByToken/{token}"})
    public ResponseEntity<ResponseDTO> getUserIdByToken(@PathVariable String token) {
        Long cartId = cartService.getUserIdByToken(token);
        ResponseDTO respDTO = new ResponseDTO("Get cartId Call Successfully", cartId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

}