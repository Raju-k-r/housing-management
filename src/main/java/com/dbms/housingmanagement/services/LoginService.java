package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.model.Login;
import com.dbms.housingmanagement.services.model.LoginInfo;

import javax.servlet.http.HttpSession;

public interface LoginService {

    // == New Login Form ==
    LoginInfo getNewLoginForm();

    // == Login the User ==
    boolean loginTheUser(LoginInfo loginInfo, HttpSession session) throws Exception;

    // == check password ==
    boolean isPasswordMatches(String rawPassword,String encryptedString);

    // == Get the Login Data ==
    Login getLoginData(String email);
}
