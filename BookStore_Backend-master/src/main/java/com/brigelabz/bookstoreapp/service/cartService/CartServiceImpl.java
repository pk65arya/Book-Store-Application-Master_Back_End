package com.brigelabz.bookstoreapp.service.cartService;

import com.brigelabz.bookstoreapp.dto.CartDTO;
import com.brigelabz.bookstoreapp.entity.Book;
import com.brigelabz.bookstoreapp.entity.Cart;
import com.brigelabz.bookstoreapp.entity.User;
import com.brigelabz.bookstoreapp.exceptions.BookstoreException;
import com.brigelabz.bookstoreapp.repository.BookRepository;
import com.brigelabz.bookstoreapp.repository.UserRepository;
import com.brigelabz.bookstoreapp.repository.cartRepository;
import com.brigelabz.bookstoreapp.service.UserLogin.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {

    @Autowired
    private cartRepository cartrepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cart> getAllCartItems() {
        return cartrepository.findAll();
    }

    @Override
    public List<Cart> getBookStoreDataByUserId(Long userId) {
        return cartrepository.findCartsByUsersId(userId);
    }

    @Override
    public Cart addToCart(Long bookId) {
        Book book = bookRepository.getBookByBookId(bookId);
        System.out.println(book);
        Cart cart = new Cart();
        /* cart.setBooks(book);*/
        cart.setQuantity(4L);
        cart.setUsers(null);
        return cartrepository.save(cart);
    }

    @Override // working 80% but need to still do
    public Cart addDataToCart(CartDTO cartDTO) {
        Long bookId = cartDTO.getBookId();
        Long userId = cartDTO.getUserId();
        Long quantity = cartDTO.getQuantity(); // quantity
        // getting book by book ID
        Book book = bookRepository.getBookByBookId(bookId);
        // getting user by user ID
        User userById = userRepository.getUserById(userId);

        //if (quantity != 0 && book.getQuantity() >= quantity) {
            Cart cart = new Cart();
            cart.setUsers(userById);
            cart.setQuantity(quantity);
            cart.addBookToCart(book);
            return cartrepository.save(cart);
       // }
       // throw new BookstoreException("Book is out stock !!! -> ( Enter the valid quantity )");

    }

    @Override // need to work on BOOK // now it is effecting in DB but need to work
    public Cart addBooksToCartByCartID(Long cartId, Long bookId) {
        Cart cart = cartrepository.findById(cartId).get();
        Book bookByBookId = bookRepository.getBookByBookId(bookId);
        cart.addBookToCart(bookByBookId);
        return cartrepository.save(cart);
    }

    @Override // working

    public void removeCart(Long cartId) {
        cartrepository.deleteById(cartId);
    }

    @Override // working
    public Cart updateCart(Long cartId, Long quantity) {
        Cart cartData = cartrepository.getBookStoreDataByCartId(cartId);
        cartData.setQuantity(quantity);
        // modelMapper.map(cartData,cartData);
        return cartrepository.save(cartData);
    }

    @Override
    public List<Cart> getAllCartItemsUser(String token) {
        String emailID = UserLoginServiceImpl.findSubByDecodeToken(token);
        User userByEmail = userRepository.getEmailIdByEmail(emailID);
        System.out.println("Its crossing this line");
        List<Cart> cart = cartrepository.findAllByUsersId(userByEmail.getId());
        System.out.println(cart);
        return cart;
    }

    @Override
    public List<Cart> findCartsByUsers_Id(Long userId) {
        List<Cart> cartsByUsers_id = cartrepository.findCartsByUsers_Id(userId);
        return cartsByUsers_id;
    }

    @Override
    public List<Cart> getBookStoreDataByUsers_Id(Long users_id) {
        return cartrepository.getBookStoreDataByUsers_Id(users_id);
    }

    @Override
    public void removeAllCarts(String token) {
        Long userId = Long.valueOf(UserLoginServiceImpl.findSubByDecodeToken(token));
        Optional<User> user = Optional.ofNullable(userRepository.getUserById(userId));
        if (user.isPresent()) {
            List<Cart> carts = cartrepository.findCartsByUsers_Id(userId);
            cartrepository.deleteAll(carts);
        }
    }

    @Override
    public List<Book> getTokenByEmail(String token){
        String email = UserLoginServiceImpl.findSubByDecodeToken(token);
        User user = userRepository.getEmailIdByEmail(email);
        Long cartId = user.getCarts().get(0).getCartId();
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < user.getCarts().size(); i++) {
            List<Book> books = user.getCarts().get(i).getBooks();
            for (int j = 0; j < books.size(); j++) {
                 Book book = books.get(j);
                bookList.add(book);

            }
        }
        System.out.println(cartId);
        return bookList;
    }

    @Override
    public String deleteBookByBook_Id(Long books_book_id) {
        cartrepository.deleteBookByBook_Id(books_book_id);
        return "The deleted book Id "+ books_book_id;

    }

    @Override
    public Long getUserIdByToken(String token) {

            String email = UserLoginServiceImpl.findSubByDecodeToken(token);
            User user = userRepository.getEmailIdByEmail(email);
            Long userId = user.getId();
            return userId;
        }

}

