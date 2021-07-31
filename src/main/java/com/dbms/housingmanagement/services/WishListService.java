package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.services.model.WishList;
import org.springframework.security.crypto.codec.Base64;

import java.nio.charset.StandardCharsets;
import java.util.List;

public interface WishListService {

    // == Add the Wish List ==
    boolean addToWishList(int userId, int propertyId);

    // == Get All my wish list ==
    List<WishList> getAllMyWishList(int userId);

    // == Is Property in wish List ==
    boolean isPropertyInWishList(int userId, int propertyId);

    // == Used to convert image to Base64 ==
    default String getPicture(byte[] profilePicture){
        return new String(Base64.encode(profilePicture), StandardCharsets.UTF_8);
    }

    // == Removing the wish list ==
    boolean removeWishList(int propertyId, int userId);
}
