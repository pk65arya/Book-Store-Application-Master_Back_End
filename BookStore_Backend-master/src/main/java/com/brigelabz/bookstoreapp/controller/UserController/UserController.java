package com.brigelabz.bookstoreapp.controller.UserController;

import com.brigelabz.bookstoreapp.dto.ResponseDTO;
import com.brigelabz.bookstoreapp.dto.UserDTO;
import com.brigelabz.bookstoreapp.entity.User;
import com.brigelabz.bookstoreapp.service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping(value = {"","/","/get"})
    public ResponseEntity<ResponseDTO> getAllUsers(){
        List<User> allUsers = userServiceImpl.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", allUsers);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping( "/getById/{userId}")
    public ResponseEntity<ResponseDTO> getUserDataById(@PathVariable("userId") long userId){
        User userById = userServiceImpl.getUserById(userId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful at ID: " + userId, userById);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addPersonData(@Valid @RequestBody UserDTO userDTO){
        User user = userServiceImpl.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Create Person Data for: " ,user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updatePersonDataById(@PathVariable("userId") long userId, @Valid @RequestBody UserDTO userDTO){
       userDTO.setUserId(userId);
        User user = userServiceImpl.updateUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Person with id  " + userId, user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDTO> deletePersonDataById(@PathVariable long userId){
        userServiceImpl.deleteUser(userId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted User with id  " + userId,"");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}