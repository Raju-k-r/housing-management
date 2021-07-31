package com.dbms.housingmanagement.utils;

public final class Mapping {

    // == Constants ==
    public static final String HOME = "/";
    public static final String LOGIN = "/login";
    public static final String REDIRECT_TO_HOME_PAGE = "redirect:" + HOME;
    public static final String REDIRECT_TO_LOGIN_PAGE = "redirect:" + LOGIN;
    public static final String REGISTER = "/register";
    public static final String MY_WISH_LIST = "/getMyWishList";
    public static final String ADD_NEW_POST = "/addPost";
    public static final String REDIRECT_TO_MY_WISH_LIST = "redirect:" + MY_WISH_LIST;
    public static final String LOGOUT = "/logout";
    public static final String ADD_TO_PROPERTY_TO_WISH_LIST = "/addToWishList/{propertyId}";
    public static final String DELETE_POST = "/deletePost/{propertyId}";
    public static final String REMOVE_WISH_LIST = "/removeWishList/{propertyId}";

    public static final String MY_POSTS = "/myPosts";
}
