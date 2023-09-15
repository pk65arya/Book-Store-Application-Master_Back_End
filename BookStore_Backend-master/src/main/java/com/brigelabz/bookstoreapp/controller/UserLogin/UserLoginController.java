package com.brigelabz.bookstoreapp.controller.UserLogin;

import com.brigelabz.bookstoreapp.dto.ResponseDTO;
import com.brigelabz.bookstoreapp.entity.User;
import com.brigelabz.bookstoreapp.entity.UserLogin;
import com.brigelabz.bookstoreapp.service.UserLogin.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/authentication")
public class UserLoginController {

    @Autowired
    private UserLoginServiceImpl userLoginServiceImpl;

    // Login api
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> checkLoginPersonAccountByEmailAndPassword(@RequestBody UserLogin userLogin){
        String checkLoginUserAccountByEmailAndPasswordAndRole = userLoginServiceImpl.checkLoginUserAccountByEmailAndPasswordAndRole(userLogin);
        ResponseDTO responseDTO = new ResponseDTO("User has been successfully login : ",checkLoginUserAccountByEmailAndPasswordAndRole);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // send OTP to email
    @PostMapping("/sendOTPToEmail")
    public ResponseEntity<ResponseDTO> sendOTPToEmail(@RequestBody UserLogin userLogin){
        Long otpToEmail = userLoginServiceImpl.sendOTPToEmail(userLogin.getEmail());
        ResponseDTO responseDTO = new ResponseDTO("OTP (One Time Password) has been successfully sent : ",otpToEmail);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // verify OTP that sent to email
    @PostMapping("/verify_otp")
    public ResponseEntity<ResponseDTO> verifyOTPFromEmail(@RequestBody UserLogin userLogin){
        Long otpFromEmail = userLoginServiceImpl.verifyOTPFromEmail(userLogin.getOtp());
        ResponseDTO responseDTO = new ResponseDTO("OTP (One Time Password) has been successfully sent : ",otpFromEmail);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // verify OTP that sent to email
    @PutMapping("/rest_password")
    public ResponseEntity<ResponseDTO> changeExistingPasswordInDatabases(@RequestBody UserLogin userLogin){
        User passwordRestedForUser = userLoginServiceImpl.changeExistingPasswordInDatabases(userLogin);
        ResponseDTO responseDTO = new ResponseDTO(" New password successfully updated in database -> {} : ",passwordRestedForUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
