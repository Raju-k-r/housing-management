package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.Login;

import java.util.List;

public interface LoginDao {

    // == Get all Login ==
    List<Login> getAllLogins();

    // == Get login using email ==
    Login getLogin(String email);

    // == Add new Login ==
    boolean addNewLogin(Login login);

    // == Update Login ==
    boolean updateLogin(Login login);

    // == Delete Login ==
    boolean deleteLogin(Login login);

}
