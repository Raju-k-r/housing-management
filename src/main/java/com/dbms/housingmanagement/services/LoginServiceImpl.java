package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.LoginDao;
import com.dbms.housingmanagement.dao.RegistrationDao;
import com.dbms.housingmanagement.dao.model.Login;
import com.dbms.housingmanagement.services.model.LoginInfo;
import com.dbms.housingmanagement.utils.SessionVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    // == Fields ==
    private LoginDao loginDao;
    private RegistrationDao registrationDao;

    // == LoginDao Setter ==
    @Autowired
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    // == RegistrationDao setter ==
    @Autowired
    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }


    // == Public Methods ==

    // == Creating a model class and returning ==
    @Override
    public LoginInfo getNewLoginForm() {
        // == Creating the Model class and returning the class ==
        return new LoginInfo();
    }

    // == Used to login the User ==
    @Override
    public boolean loginTheUser(LoginInfo loginInfo,HttpSession session) throws Exception {
        Login userLoginInfo = getLoginData(loginInfo.getEmail());
        log.info("UserLoginInfo -> {}" , userLoginInfo);
        if(userLoginInfo == null){
            throw new Exception("Invalid Email");
        }

        if (!isPasswordMatches(loginInfo.getPassword(), userLoginInfo.getPassword())) {
            throw new Exception("Invalid Password");
        }

        // == Setting the Attributes in Session Variables ==
        if(session != null){
            log.info("Setting the Session Variables");
            session.setAttribute(SessionVariables.IS_USER_LOGGED_IN, true);
            session.setAttribute(SessionVariables.USER_ID, userLoginInfo.getUserId());
            session.setAttribute(SessionVariables.USER_TYPE, userLoginInfo.getUserType());
            session.setAttribute(SessionVariables.USER,registrationDao.getUser(userLoginInfo.getEmail()));
            session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
            session.setAttribute(SessionVariables.MESSAGE_TYPE, "SUCCESS");
            session.setAttribute(SessionVariables.MESSAGE,"User Logged in Successfully");
        }else{
            log.info("Session is Null in side Login User");
        }
        return true;
    }

    @Override
    public boolean isPasswordMatches(String rawPassword, String encryptedString) {

        // == Creating Password Encryptor ==
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // == Checking the Password and returning the result ==
        return encoder.matches(rawPassword, encryptedString);
    }

    @Override
    public Login getLoginData(String email) {
        return loginDao.getLogin(email);
    }
}
