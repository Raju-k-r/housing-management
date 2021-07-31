package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.LoginDao;
import com.dbms.housingmanagement.dao.RegistrationDao;
import com.dbms.housingmanagement.services.model.RegistrationInfo;

public interface RegistrationService {

    RegistrationInfo getNewRegistrationForm();

    boolean addForm(RegistrationInfo registrationInfo) throws Exception;

    void setRegistrationDao(RegistrationDao registrationDao);

    void setLoginDao(LoginDao loginDao);
}
