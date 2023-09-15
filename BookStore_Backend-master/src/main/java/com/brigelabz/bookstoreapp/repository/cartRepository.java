package com.brigelabz.bookstoreapp.repository;

import com.brigelabz.bookstoreapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface cartRepository extends JpaRepository<Cart,Long> {

        List<Cart> findAllByUsersId(Long userId);
        Cart getBookStoreDataByCartId(Long cartId);
        Cart deleteCartByCartId(Long cartId);
        // same
        List<Cart> findCartsByUsersId(Long users_id);
        // same
        List<Cart> findCartsByUsers_Id(Long users_id);

        List<Cart> getBookStoreDataByUsers_Id(Long users_id);

        List<Cart> getAllByUsers_Id(Long users_id);
        //delete from cart_books where books_book_id = 4;
        @Modifying
        @Transactional
        @Query(value = " delete from cart_books where books_book_id= :books_book_id",nativeQuery = true)
        void deleteBookByBook_Id(Long books_book_id);

    }
