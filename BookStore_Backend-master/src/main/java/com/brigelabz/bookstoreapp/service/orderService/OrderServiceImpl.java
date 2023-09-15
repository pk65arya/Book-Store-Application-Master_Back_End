package com.brigelabz.bookstoreapp.service.orderService;

import com.brigelabz.bookstoreapp.dto.OrderDTO;
import com.brigelabz.bookstoreapp.entity.Book;
import com.brigelabz.bookstoreapp.entity.Order;
import com.brigelabz.bookstoreapp.entity.User;
import com.brigelabz.bookstoreapp.repository.BookRepository;
import com.brigelabz.bookstoreapp.repository.OrderRepository;
import com.brigelabz.bookstoreapp.repository.UserRepository;
import com.brigelabz.bookstoreapp.service.UserLogin.UserLoginServiceImpl;
import com.brigelabz.bookstoreapp.service.cartService.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
//    static String TOKEN = null;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ICartService iCartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Books added in placeorder
    @Override
    public Order placeOrder(String token, OrderDTO orderDTO) {

            Long price = orderDTO.getPrice();
            Long quantity = orderDTO.getQuantity();
            String address = orderDTO.getAddress();
          List<Book> bookList = iCartService.getTokenByEmail(token);
          String subByDecodeToken = UserLoginServiceImpl.findSubByDecodeToken(token);
          User userOrder = userRepository.getEmailIdByEmail(subByDecodeToken);
           Order order = new Order();
           order.setPrice(price);
           order.setQuantity(quantity);
           order.setAddress(address);
           order.setUserOrder(userOrder);
           order.setBooks(bookList);
            return orderRepository.save(order);


//            Long bookId = orderDTO
//            Long userId = orderDTO.getUserId();
//            Long quantity = orderDTO.getQuantity(); // quantity
//            // getting book by book ID
//            Book book = bookRepository.getBookByBookId(bookId);
//            // getting user by user ID
//            User userById = userRepository.getUserById(userId);
//
//            //if (quantity != 0 && book.getQuantity() >= quantity) {
//            Order order = new Order();
//            order.setPrice(price);
//            order.setAddress(order.getAddress());
//            order.addBookToOrder(book);
//            return orderRepository.save(order);
    }

    @Override
    public Boolean cancelOrder(String token, Long orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUserId(String token) {
        String emailID = UserLoginServiceImpl.findSubByDecodeToken(token);
        User userByEmail = userRepository.getEmailIdByEmail(emailID);
        List<Order> order = orderRepository.findOrdersByUserId(userByEmail.getId());
        System.out.println(order);
        return order;
    }

    @Override
    public User getUserByToken(String token) {
        String useremail = UserLoginServiceImpl.findSubByDecodeToken(token);
        User emailIdByEmail = userRepository.getEmailIdByEmail(useremail);
        return emailIdByEmail;
    }

    @Override
    public String sendOrderEmail(String token) {
        String subject="Order Confirmed success";
        String message = "Order successfully placed";
        String email = UserLoginServiceImpl.findSubByDecodeToken(token);
        String toEmail = email;
        String fromEmail= "pk65arya@gmail.com";
        UserLoginServiceImpl.sendEmail(subject, message,fromEmail,toEmail);
        return "Order successfully placed";
    }
}