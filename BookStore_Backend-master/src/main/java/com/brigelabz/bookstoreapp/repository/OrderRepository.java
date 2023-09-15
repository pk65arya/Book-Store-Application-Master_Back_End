package com.brigelabz.bookstoreapp.repository;


import com.brigelabz.bookstoreapp.entity.Order;
import com.brigelabz.bookstoreapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "select * from order_table where user_order_id = :user_order_id",nativeQuery = true)
    List<Order> findOrdersByUserId(Long user_order_id);
    
}
