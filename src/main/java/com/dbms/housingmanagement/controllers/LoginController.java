package com.dbms.housingmanagement.controllers;

import com.dbms.housingmanagement.services.LoginService;
import com.dbms.housingmanagement.services.model.LoginInfo;
import com.dbms.housingmanagement.utils.Mapping;
import com.dbms.housingmanagement.utils.ModelAttributes;
import com.dbms.housingmanagement.utils.SessionVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.dbms.housingmanagement.utils.Mapping.*;
import static com.dbms.housingmanagement.utils.ModelAttributes.LOGIN_FORM;
import static com.dbms.housingmanagement.utils.Views.LOGIN_PAGE;

@Slf4j
@Controller
public class LoginController {

    // == Fields ==
    private LoginService service;

    // == Injecting the Service Class ==
    @Autowired
    public void setLoginControllerService(LoginService service){
        this.service = service;
    }

    @GetMapping(LOGIN)
    public String getLoginPage(Model model, HttpSession session){

        // == Setting the Model attributes from session ==
        if(session.getAttribute(SessionVariables.IS_ANY_MESSAGE)!= null && (boolean) session.getAttribute(SessionVariables.IS_ANY_MESSAGE)){
            // == Setting the Message ==
            model.addAttribute(ModelAttributes.IS_ANY_MESSAGE, true);
            model.addAttribute(ModelAttributes.MESSAGE, session.getAttribute(SessionVariables.MESSAGE));
        }

        // == Removing the variables from session ==
        session.removeAttribute(SessionVariables.IS_ANY_MESSAGE);
        session.removeAttribute(SessionVariables.MESSAGE);

        log.info("/Login");
        if(session.getAttribute(SessionVariables.IS_USER_LOGGED_IN) == null) {
            model.addAttribute(LOGIN_FORM, service.getNewLoginForm());
            return LOGIN_PAGE;
        }
        return REDIRECT_TO_HOME_PAGE;
    }

    @PostMapping(LOGIN)
    public String postLoginPage(@Valid @ModelAttribute(LOGIN_FORM) LoginInfo loginInfo,
                                BindingResult bindingResult, Model model, HttpSession session){
        log.info("Post /Login");

        if (bindingResult.hasErrors()) {
            model.addAttribute(ModelAttributes.FROM_ERROR, bindingResult.getAllErrors());
            return LOGIN_PAGE;
        }

        try{
            if(service.loginTheUser(loginInfo,session)){
                log.info("Login Was Successful");
                return REDIRECT_TO_HOME_PAGE;
            }
        }catch (Exception e){
            model.addAttribute(ModelAttributes.LOGIN_ERROR_MESSAGE, e.getMessage());
            e.printStackTrace();
            return LOGIN_PAGE;
        }
        model.addAttribute(ModelAttributes.LOGIN_ERROR_MESSAGE, "Something went wrong !");
        return LOGIN_PAGE;
    }

    @GetMapping(Mapping.LOGOUT)
    public String logOut(HttpSession session){
        session.invalidate();
        return REDIRECT_TO_LOGIN_PAGE;
    }
}
