package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.Registration;

import java.util.List;

public interface RegistrationDao {

    // == Get all Registered User ==
    List<Registration> getAllRegisteredUser();

    // == Get user based on Email ==
    Registration getUser(String email);

    // == Get user based on the Id ==
    Registration getUser(int userId);

    // == Update the User Details ==
    boolean updateUser(Registration registration);

    // == Add new User ==
    boolean addNewUser(Registration registration);

    // == Delete User ==
    boolean deleteUser(Registration registration);

    // == Get userId from email ==
    int getUserId(String email);
}
