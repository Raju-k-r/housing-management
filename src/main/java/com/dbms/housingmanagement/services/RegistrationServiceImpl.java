package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.LoginDao;
import com.dbms.housingmanagement.dao.RegistrationDao;
import com.dbms.housingmanagement.dao.model.Login;
import com.dbms.housingmanagement.dao.model.Registration;
import com.dbms.housingmanagement.services.model.RegistrationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationDao registrationDao;
    private LoginDao loginDao;

    public RegistrationInfo getNewRegistrationForm() {
        return new RegistrationInfo();
    }

    @Transactional
    public boolean addForm(RegistrationInfo registration) throws Exception{
        // == Creating new Registration model using RegistrationInfo ==
        Registration newRegistration = Registration.createRegistration(registration);

        // == Checking if the user already present ==
        if(registrationDao.getUser(newRegistration.getEmail()) != null){
            throw new Exception("User with this Email already present..");
        }

        // == Adding new User to user table ==
        if (registrationDao.addNewUser(newRegistration)) {
            log.info("User Added to user table..");

            // == Adding data to login table ==
            if (loginDao.addNewLogin(new Login(registrationDao.getUserId(newRegistration.getEmail()),
                    registration.getFirstName() + registration.getLastName(),
                    registration.getEmail(),
                    registration.getPassword()))) {
                log.info("User Added to Login Table..");
                return true;
            }
        }
        return false;
    }

    @Override
    @Autowired
    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Override
    @Autowired
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public List<Registration> getAllRegistration(){
        return registrationDao.getAllRegisteredUser();
    }
}
