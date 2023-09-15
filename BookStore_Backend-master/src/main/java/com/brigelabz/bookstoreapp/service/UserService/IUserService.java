package com.brigelabz.bookstoreapp.service.UserService;

import com.brigelabz.bookstoreapp.dto.UserDTO;
import com.brigelabz.bookstoreapp.entity.User;

import java.util.List;


public interface IUserService {

    List<User> getAllUsers();
    User getUserById(Long userId);
    User addUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO);
    String deleteUser(Long userId);
}
