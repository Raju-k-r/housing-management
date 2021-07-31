package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.WishList;

import java.util.List;

public interface WishListDao {

    // == Add wish list ==
    boolean addWishList(int userId, int propertyId);

    // == Get all Wish List of user==
    List<WishList> getAllWishList(int userId);

    // == Is Property in wish List ==
    List<WishList> isPropertyInWishList(int userId, int propertyId);

    // == Remove the wish list ==
    boolean removeTheWishList(int propertyId, int userId);

    // == Remove the wish list ==
    boolean removeTheWishList(int propertyId);

    List<WishList> getAllWishList(String propertyId);
}
