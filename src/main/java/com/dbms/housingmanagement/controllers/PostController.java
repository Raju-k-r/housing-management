package com.dbms.housingmanagement.controllers;

import com.dbms.housingmanagement.dao.model.Registration;
import com.dbms.housingmanagement.services.PostService;
import com.dbms.housingmanagement.services.model.PostInfo;
import com.dbms.housingmanagement.utils.Mapping;
import com.dbms.housingmanagement.utils.ModelAttributes;
import com.dbms.housingmanagement.utils.SessionVariables;
import com.dbms.housingmanagement.utils.Views;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class PostController {

    // == Fields ==
    private PostService postService;

    @GetMapping(Mapping.ADD_NEW_POST)
    public String getAddPostForm(Model model, HttpSession session) {

        // == Checking if user logged in or not ==
        if (session.getAttribute(SessionVariables.IS_USER_LOGGED_IN) == null) {
            return Mapping.REDIRECT_TO_LOGIN_PAGE;
        }

        // == adding the model attributes ==
        model.addAttribute(ModelAttributes.NEW_POST_FORM, postService.getNewPostForm());
        model.addAttribute(ModelAttributes.USER, session.getAttribute(SessionVariables.USER));

        // == Returning the view ==
        return Views.ADD_POST;
    }

    @PostMapping(Mapping.ADD_NEW_POST)
    public String addNewPost(@Valid @ModelAttribute(ModelAttributes.NEW_POST_FORM) PostInfo postInfo,
                             @RequestParam("image") MultipartFile[] multipartFile,
                             BindingResult result, Model model, HttpSession session) {

        // == Fetching the Image ==

        List<byte[]> images = new ArrayList<>();

        try {

            for (MultipartFile file : multipartFile) {
                images.add(file.getBytes());
            }

        } catch (Exception e) {
            images = null;
            log.info("Multi Part File Exception -> {}", e.getMessage());
            e.printStackTrace();
        }

        log.info("Post Info -> {}", postInfo);

        if (result.hasErrors()) {
            model.addAttribute(ModelAttributes.FROM_ERROR, result.getAllErrors());
            return Views.ADD_POST;
        }

        if (session.getAttribute(SessionVariables.IS_USER_LOGGED_IN) == null) {
            return Mapping.REDIRECT_TO_LOGIN_PAGE;
        }

        try {
            if (postService.addNewPost(postInfo, ((Registration) session.getAttribute(SessionVariables.USER)), images)) {
                log.info("Post Added Successfully");
                // == Adding session variables ==
                session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
                session.setAttribute(SessionVariables.MESSAGE_TYPE, "SUCCESS");
                session.setAttribute(SessionVariables.MESSAGE,"Post Added Successfully...");
            }
            return Mapping.REDIRECT_TO_HOME_PAGE;

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(ModelAttributes.NEW_FORM_ERROR_MESSAGE, e.getMessage());
            return Views.ADD_POST;
        }
    }



    @GetMapping(Mapping.DELETE_POST)
    public String deletePost(@PathVariable String propertyId, HttpSession session){

        // == Deleting the Post ==
        if (postService.deletePost(Integer.parseInt(propertyId))) {
            // == Adding session variables ==
            session.setAttribute(SessionVariables.IS_ANY_MESSAGE, true);
            session.setAttribute(SessionVariables.MESSAGE,"Post Deleted Successfully...");
        }

        // == Redirecting to home page ==
        return Mapping.REDIRECT_TO_HOME_PAGE;
    }

    // == PostService Setter ==
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
