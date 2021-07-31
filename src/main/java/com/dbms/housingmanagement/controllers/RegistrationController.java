package com.dbms.housingmanagement.controllers;

import com.dbms.housingmanagement.services.RegistrationService;
import com.dbms.housingmanagement.services.model.RegistrationInfo;
import com.dbms.housingmanagement.utils.SessionVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.dbms.housingmanagement.utils.Mapping.REDIRECT_TO_HOME_PAGE;
import static com.dbms.housingmanagement.utils.Mapping.REGISTER;
import static com.dbms.housingmanagement.utils.ModelAttributes.*;
import static com.dbms.housingmanagement.utils.Views.REGISTRATION_PAGE;

@Slf4j
@Controller
public class RegistrationController {

    private RegistrationService service;

    @Autowired
    public void setService(RegistrationService service) {
        this.service = service;
    }

    @GetMapping(REGISTER)
    public String getRegistration(Model model){
        log.info("Inside the /Registration");
        model.addAttribute(REGISTRATION_FORM, service.getNewRegistrationForm());
        return REGISTRATION_PAGE;
    }

    @PostMapping(REGISTER)
    public String postRegistration(@ModelAttribute(REGISTRATION_FORM) @Valid RegistrationInfo registration, BindingResult bindingResult, @RequestParam("profilePhoto") MultipartFile file, Model model, HttpSession session){
        log.info("Inside the /Registration");
        log.info("Registration {}" ,registration);

        // == Checking bindingResult errors ==
        log.info("Binding Results -> {}", bindingResult.getAllErrors());
        if(bindingResult.hasErrors()){
            log.info("Binding the errors ->{}", bindingResult.getAllErrors());
            model.addAttribute(FROM_ERROR, bindingResult.getAllErrors());
            return REGISTRATION_PAGE;
        }

        // == Checking password and confirm password ==
        if(!registration.getPassword().equals(registration.getConfirmPassword())){
            log.info("Adding the REGISTRATION_ERROR_MESSAGE");
            model.addAttribute(REGISTRATION_ERROR_MESSAGE, "Password and Confirm password Doesn't match");
            return REGISTRATION_PAGE;
        }

        try {

            // == Adding Profile image ==
            byte[] image = file.getBytes();

            registration.setProfilePicture(image);

            // == Adding the registration ==
            if (service.addForm(registration)) {
                log.info("User added -> {}", true);

                // == Setting the Session Variables ==
                session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
                session.setAttribute(SessionVariables.MESSAGE, "User Registered Successfully...");

                return REDIRECT_TO_HOME_PAGE;
            } else {
                log.info("User not added -> {}", false);
                model.addAttribute(REGISTRATION_ERROR_MESSAGE, "Something went wrong");
                return REGISTRATION_PAGE;
            }
        }catch (Exception e){
            model.addAttribute(REGISTRATION_ERROR_MESSAGE, e.getMessage());
            e.printStackTrace();
            return REGISTRATION_PAGE;
        }
    }
}
