package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.*;
import com.dbms.housingmanagement.dao.model.*;
import com.dbms.housingmanagement.services.model.PostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    // == Fields ==
    private PostDao postDao;
    private OwnerDao ownerDao;
    private PropertiesDao propertiesDao;
    private PropertiesImagesDao propertiesImagesDao;
    private WishListDao wishListDao;

    // == Public Methods ==

    // == Getting new Post Form ==
    @Override
    public PostInfo getNewPostForm() {
        return new PostInfo();
    }

    // == Adding the new Post ==
    @Override
    @Transactional
    public boolean addNewPost(PostInfo postInfo, Registration user, List<byte[]> images) throws Exception {

        // == Getting the Owner using email ==
        Owner owner = ownerDao.getOwner(postInfo.getEmail());

        // == Checking if the Owner is Equal to Existing Owner ==
        if (owner == null) {
            if (ownerDao.addNewOwner(Owner.createObject(postInfo))) {
                // == Getting Owner ==
                owner = ownerDao.getOwner(postInfo.getEmail());

                // == Adding the Property ==
                propertiesDao.addNewProperty(Property.createFromPropertyInfo(postInfo, owner.getOwnerId()));

                // == Getting the Property ==
                Property property = propertiesDao.getProperty(Property.createFromPropertyInfo(postInfo, owner.getOwnerId()));

                for (byte[] image : images) {
                    // == Creating propertiesImage object ==
                    PropertiesImage propertiesImage = PropertiesImage.createFromImageAndPropertyId(image, property.getPropertyId());

                    // == Adding Property image ==
                    propertiesImagesDao.addNewImage(propertiesImage);
                }

                return postDao.addNewPost(Post.createObject(user.getUserId(), owner.getOwnerId(), property.getPropertyId()));

            } else {
                throw new Exception("Something Went wrong while adding Property");
            }
        } else if (Owner.createObject(postInfo).equals(owner)) {

            // == Adding new property ==
            propertiesDao.addNewProperty(Property.createFromPropertyInfo(postInfo, owner.getOwnerId()));

            // == Getting the Property ==
            Property property = propertiesDao.getProperty(Property.createFromPropertyInfo(postInfo, owner.getOwnerId()));

            for (byte[] image : images) {
                // == Creating propertiesImage object ==
                PropertiesImage propertiesImage = PropertiesImage.createFromImageAndPropertyId(image, property.getPropertyId());

                // == Adding Property image ==
                propertiesImagesDao.addNewImage(propertiesImage);
            }

            return postDao.addNewPost(Post.createObject(user.getUserId(), owner.getOwnerId(), property.getPropertyId()));
        } else {
            throw new Exception("Owner with this Email already Exists Please Use different Email");
        }
    }

    @Override
    public boolean addNewImage(byte[] image, int propertyId) {

        // == Creating new Model class ==
        PropertiesImage newImage = new PropertiesImage();

        // == Setting Values ==
        newImage.setPropertyId(propertyId);
        newImage.setBlob(image);

        // == Adding Image to database and returning result ==
        return propertiesImagesDao.addNewImage(newImage);
    }

    @Override
    @Transactional
    public boolean deletePost(int propertyId) {
        return wishListDao.removeTheWishList(propertyId) && propertiesImagesDao.deletePropertyImage(propertyId) && propertiesDao.deleteProperty(propertyId) && postDao.deletePost(propertyId);
    }

    // == Setters ==
    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setOwnerDao(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Autowired
    public void setPropertiesDao(PropertiesDao propertiesDao) {
        this.propertiesDao = propertiesDao;
    }

    @Autowired
    public void setPropertiesImagesDao(PropertiesImagesDao propertiesImagesDao) {
        this.propertiesImagesDao = propertiesImagesDao;
    }

    @Autowired
    public void setWishListDao(WishListDao wishListDao) {
        this.wishListDao = wishListDao;
    }
}
