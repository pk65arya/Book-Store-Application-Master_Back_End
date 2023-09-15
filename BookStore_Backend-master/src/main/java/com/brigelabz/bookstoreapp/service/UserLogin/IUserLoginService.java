package com.brigelabz.bookstoreapp.service.UserLogin;


import com.brigelabz.bookstoreapp.entity.User;
import com.brigelabz.bookstoreapp.entity.UserLogin;

public interface IUserLoginService {

    String checkLoginUserAccountByEmailAndPasswordAndRole(UserLogin userLogin);
    Long sendOTPToEmail(String email);
    Long verifyOTPFromEmail(long InputOtp);
    User changeExistingPasswordInDatabases(UserLogin userLogin);
}