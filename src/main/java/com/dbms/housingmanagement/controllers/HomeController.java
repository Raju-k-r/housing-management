package com.dbms.housingmanagement.controllers;

import com.dbms.housingmanagement.dao.model.Registration;
import com.dbms.housingmanagement.services.HomeService;
import com.dbms.housingmanagement.utils.Mapping;
import com.dbms.housingmanagement.utils.ModelAttributes;
import com.dbms.housingmanagement.utils.SessionVariables;
import com.dbms.housingmanagement.utils.Views;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.dbms.housingmanagement.utils.Mapping.HOME;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(HOME)
    public String getHomePage(Model model, HttpSession session){


        // == Checking if the user login or not ==
        if(session.getAttribute(SessionVariables.IS_USER_LOGGED_IN) == null){
            return Mapping.REDIRECT_TO_LOGIN_PAGE;
        }

        // == Adding the User ==
        model.addAttribute(ModelAttributes.USER, session.getAttribute(SessionVariables.USER));
        model.addAttribute(ModelAttributes.USER_TYPE, session.getAttribute(SessionVariables.USER_TYPE));

        // == Getting userId ==
        int userId = (int) session.getAttribute(SessionVariables.USER_ID);

        // == Adding the Posts ==
        model.addAttribute(ModelAttributes.POSTS, homeService.getOthersPost(String.valueOf(userId)));

        // == Setting the Model attributes from session ==
        if(session.getAttribute(SessionVariables.IS_ANY_MESSAGE)!= null && (boolean) session.getAttribute(SessionVariables.IS_ANY_MESSAGE)){
            // == Setting the Message ==
            model.addAttribute(ModelAttributes.IS_ANY_MESSAGE, true);
            model.addAttribute(ModelAttributes.MESSAGE, session.getAttribute(SessionVariables.MESSAGE));
            model.addAttribute(ModelAttributes.MESSAGE_TYPE, session.getAttribute(SessionVariables.MESSAGE_TYPE));
        }

        // == Removing the variables from session ==
        session.removeAttribute(SessionVariables.IS_ANY_MESSAGE);
        session.removeAttribute(SessionVariables.MESSAGE);
        session.removeAttribute(SessionVariables.MESSAGE_TYPE);

        // == Adding the Profile Image ==
        model.addAttribute(ModelAttributes.PROFILE_PHOTO, homeService.getPicture(((Registration) session.getAttribute(SessionVariables.USER)).getProfilePicture()));

        // == Returning the view ==
        return Views.HOME_PAGE;
    }

}
