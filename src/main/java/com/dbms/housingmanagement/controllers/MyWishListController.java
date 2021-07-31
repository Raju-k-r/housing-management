package com.dbms.housingmanagement.controllers;

import com.dbms.housingmanagement.dao.model.Registration;
import com.dbms.housingmanagement.services.WishListService;
import com.dbms.housingmanagement.utils.Mapping;
import com.dbms.housingmanagement.utils.ModelAttributes;
import com.dbms.housingmanagement.utils.SessionVariables;
import com.dbms.housingmanagement.utils.Views;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MyWishListController {

    // == Fields ==
    private WishListService wishListService;

    @GetMapping(Mapping.ADD_TO_PROPERTY_TO_WISH_LIST)
    public String addToWishList(@PathVariable String propertyId, HttpSession session){

        // == Getting the userId from the Session variable ==
        int userId = ((Registration)session.getAttribute(SessionVariables.USER)).getUserId();

        // == Checking if property in wish list ==
        if(wishListService.isPropertyInWishList(userId,Integer.parseInt(propertyId))){
            // == Setting the Message ==
            session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
            session.setAttribute(SessionVariables.MESSAGE, "Property is Already in WishList..");
            session.setAttribute(SessionVariables.MESSAGE_TYPE, "FAIL");
        }else{
            // == Adding the wishlist ==
            if(wishListService.addToWishList(userId, Integer.parseInt(propertyId))){
                // == Setting the Message ==
                session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
                session.setAttribute(SessionVariables.MESSAGE_TYPE, "SUCCESS");
                session.setAttribute(SessionVariables.MESSAGE, "Property added to WishList..");
            }
        }

        return Mapping.REDIRECT_TO_HOME_PAGE;
    }

    @GetMapping(Mapping.MY_WISH_LIST)
    public String getMyWishList(HttpSession session,Model model){

        // == Checking if the user login or not ==
        if(session.getAttribute(SessionVariables.IS_USER_LOGGED_IN) == null){
            return Mapping.REDIRECT_TO_LOGIN_PAGE;
        }

        // == Setting the Model attributes from session ==
        if(session.getAttribute(SessionVariables.IS_ANY_MESSAGE)!= null && (boolean) session.getAttribute(SessionVariables.IS_ANY_MESSAGE)){
            // == Setting the Message ==
            model.addAttribute(ModelAttributes.IS_ANY_MESSAGE, true);
            model.addAttribute(ModelAttributes.MESSAGE, session.getAttribute(SessionVariables.MESSAGE));
        }

        // == Removing the variables from session ==
        session.removeAttribute(SessionVariables.IS_ANY_MESSAGE);
        session.removeAttribute(SessionVariables.MESSAGE);

        // == Getting the userId from the Session variable ==
        int userId = ((Registration)session.getAttribute(SessionVariables.USER)).getUserId();

        // == Adding the User ==
        model.addAttribute(ModelAttributes.USER, session.getAttribute(SessionVariables.USER));

        // == Adding the Profile Image ==
        model.addAttribute(ModelAttributes.PROFILE_PHOTO, wishListService.getPicture(((Registration) session.getAttribute(SessionVariables.USER)).getProfilePicture()));

        // == Getting all Wish List ==
        model.addAttribute(ModelAttributes.USER_WISH_LIST,wishListService.getAllMyWishList(userId));

        // == Returning the View ==
        return Views.WISH_LIST;

    }

    @GetMapping(Mapping.REMOVE_WISH_LIST)
    public String removeWishList(@PathVariable String propertyId, HttpSession session){

        // == Getting user id from session variables ==
        int userId = ((Registration) session.getAttribute(SessionVariables.USER)).getUserId();

        // == Removing the property from wishlist ==
        if (wishListService.removeWishList(Integer.parseInt(propertyId), userId)) {
            // == Setting the Message ==
            session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
            session.setAttribute(SessionVariables.MESSAGE, "Property Removed from WishList..");
        }else{
            // == Setting the Message ==
            session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
            session.setAttribute(SessionVariables.MESSAGE, "Property Not Removed WishList..");
        }

        // == Redirecting to MY_WISH_LIST Page ==
        return Mapping.REDIRECT_TO_MY_WISH_LIST;
    }

    // == Setting the WishListService ==
    @Autowired
    public void setWishListService(WishListService wishListService) {
        this.wishListService = wishListService;
    }
}
