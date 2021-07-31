package com.dbms.housingmanagement.controllers;

import com.dbms.housingmanagement.dao.model.Registration;
import com.dbms.housingmanagement.services.HomeService;
import com.dbms.housingmanagement.utils.Mapping;
import com.dbms.housingmanagement.utils.ModelAttributes;
import com.dbms.housingmanagement.utils.SessionVariables;
import com.dbms.housingmanagement.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MyPostsController {

    private HomeService homeService;

    @GetMapping(Mapping.MY_POSTS)
    public String getMyPosts(Model model, HttpSession session){
        // == Checking if the user login or not ==
        if(session.getAttribute(SessionVariables.IS_USER_LOGGED_IN) == null){
            return Mapping.REDIRECT_TO_LOGIN_PAGE;
        }

        // == Adding the User ==
        model.addAttribute(ModelAttributes.USER, session.getAttribute(SessionVariables.USER));

        // == Getting userId ==
        int userId = (int) session.getAttribute(SessionVariables.USER_ID);

        // == Adding the Posts ==
        model.addAttribute(ModelAttributes.POSTS, homeService.getMyPost(String.valueOf(userId)));

        // == Setting the Model attributes from session ==
        if(session.getAttribute(SessionVariables.IS_ANY_MESSAGE)!= null && (boolean) session.getAttribute(SessionVariables.IS_ANY_MESSAGE)){
            // == Setting the Message ==
            model.addAttribute(ModelAttributes.IS_ANY_MESSAGE, true);
            model.addAttribute(ModelAttributes.USER_TYPE, session.getAttribute(SessionVariables.USER_TYPE));
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
        return Views.MY_POSTS;
    }

    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }
}
