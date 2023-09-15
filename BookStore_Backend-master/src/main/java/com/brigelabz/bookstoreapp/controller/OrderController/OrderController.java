package com.brigelabz.bookstoreapp.controller.OrderController;

import com.brigelabz.bookstoreapp.dto.OrderDTO;
import com.brigelabz.bookstoreapp.dto.ResponseDTO;
import com.brigelabz.bookstoreapp.entity.Order;
import com.brigelabz.bookstoreapp.entity.User;
import com.brigelabz.bookstoreapp.service.cartService.ICartService;
import com.brigelabz.bookstoreapp.service.orderService.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @GetMapping(value = {"","/","/getallorder"})
    public ResponseEntity<ResponseDTO> getAllOrders(){
        List<Order> allOrders = orderServiceImpl.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", allOrders);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping( "/getAllOrdersForUser/{token}")
    public ResponseEntity<ResponseDTO> getAllOrdersForUser(@PathVariable String token, OrderDTO orderDTO){
        List<Order> userOrderList = orderServiceImpl.getAllOrdersByUserId(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful at ORDER DTO: " , userOrderList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/cancel-Orders-By-Id/{userId}")
    public ResponseEntity<ResponseDTO> cancleOrdersById(@RequestParam String token,@RequestParam Long orderId){
        orderServiceImpl.cancelOrder(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO("Canceled Order with id  " + orderId,"");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> addPersonData(@RequestParam String token,@RequestBody OrderDTO orderDTO){
        Order placeOrder = orderServiceImpl.placeOrder(token,orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Oder Place successfully : " ,placeOrder);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = {"/getUserByToken/{token}"})
    public ResponseEntity<ResponseDTO> getUserByToken(@PathVariable String token) {
        User user = orderServiceImpl.getUserByToken(token);
        ResponseDTO respDTO = new ResponseDTO("Get cartId Call Successfully", user);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping(value = {"/sendOrderEmail/{token}"})
    public ResponseEntity<ResponseDTO> sendOrderEmail(@PathVariable String token)
    {
        String orderEmail = orderServiceImpl.sendOrderEmail(token);
          ResponseDTO respDTO = new ResponseDTO("Order confirmation Successfully", orderEmail);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }


}