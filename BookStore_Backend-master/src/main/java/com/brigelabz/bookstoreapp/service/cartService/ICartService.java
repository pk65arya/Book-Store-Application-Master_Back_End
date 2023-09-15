package com.brigelabz.bookstoreapp.service.cartService;

import com.brigelabz.bookstoreapp.dto.BookDTO;
import com.brigelabz.bookstoreapp.dto.CartDTO;
import com.brigelabz.bookstoreapp.entity.Book;
import com.brigelabz.bookstoreapp.entity.Cart;
import com.brigelabz.bookstoreapp.entity.User;

import java.util.List;

public interface ICartService {

        List<Cart> getAllCartItems();
        Cart addToCart(Long bookId);
        Cart addDataToCart(CartDTO cartDTO);
        Cart addBooksToCartByCartID(Long cartId,Long bookId);

//        List<Cart> getAllCartItems(String token);

        List<Cart>  getBookStoreDataByUserId(Long cartId);
        void removeCart(Long cartId);
        void removeAllCarts(String token);
        Cart updateCart(Long catId, Long quantity);
        List<Cart> getAllCartItemsUser(String Token); // option for now



        List<Cart> findCartsByUsers_Id(Long userId);

        List<Cart> getBookStoreDataByUsers_Id(Long users_id);


        List<Book> getTokenByEmail(String token);
        String deleteBookByBook_Id(Long books_book_id);

        Long getUserIdByToken(String token);
}