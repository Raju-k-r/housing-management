package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.OwnerDao;
import com.dbms.housingmanagement.dao.PropertiesDao;
import com.dbms.housingmanagement.dao.PropertiesImagesDao;
import com.dbms.housingmanagement.dao.WishListDao;
import com.dbms.housingmanagement.services.model.WishList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WishListServiceImpl implements WishListService {

    // == Fields ==
    private OwnerDao ownerDao;
    private PropertiesDao propertiesDao;
    private WishListDao wishListDao;
    private PropertiesImagesDao propertiesImagesDao;

    @Override
    public boolean addToWishList(int userId, int propertyId) {
        return wishListDao.addWishList(userId, propertyId);
    }

    @Override
    public List<WishList> getAllMyWishList(int userId) {

        // == Creating a empty list ==
        List<WishList> wishLists = new ArrayList<>();

        for (com.dbms.housingmanagement.dao.model.WishList wishList : wishListDao.getAllWishList(userId)) {
            // == Creating new WishList Model class ==
            WishList myWishList = new WishList();

            // == Setting the values ==
            myWishList.setProperty(propertiesDao.getProperty(wishList.getPropertyId()));
            myWishList.setOwner(ownerDao.getOwner(myWishList.getProperty().getOwnerId()));
            myWishList.setPropertyImage(getBase64Image(propertiesImagesDao.getImage(wishList.getPropertyId()).get(0).getBlob()));
            // == Adding the newWishList to WishList List ==
            wishLists.add(myWishList);
        }

        // == Returning the List ==
        return wishLists;
    }

    @Override
    public boolean isPropertyInWishList(int userId, int propertyId) {
        log.info("In side is Property In Wish List");
        List<com.dbms.housingmanagement.dao.model.WishList> wishLists = wishListDao.isPropertyInWishList(userId,propertyId);
        log.info("Wish List -> {}", wishLists);
        return wishLists != null && wishLists.size() > 0;
    }

    @Override
    public boolean removeWishList(int propertyId, int userId) {
        return wishListDao.removeTheWishList(propertyId,userId);
    }

    // == Used to convert image to Base64 ==
    private String getBase64Image(byte[] image) {
        return new String(Base64.encode(image), StandardCharsets.UTF_8);
    }

    // == Setters ==

    @Autowired
    public void setOwnerDao(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Autowired
    public void setPropertiesDao(PropertiesDao propertiesDao) {
        this.propertiesDao = propertiesDao;
    }

    @Autowired
    public void setWishListDao(WishListDao wishListDao) {
        this.wishListDao = wishListDao;
    }

    @Autowired
    public void setPropertiesImagesDao(PropertiesImagesDao propertiesImagesDao) {
        this.propertiesImagesDao = propertiesImagesDao;
    }
}
