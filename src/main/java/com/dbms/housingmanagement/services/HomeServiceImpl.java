package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.*;
import com.dbms.housingmanagement.dao.model.Post;
import com.dbms.housingmanagement.dao.model.PropertiesImage;
import com.dbms.housingmanagement.services.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{

    // == Fields ==
    private OwnerDao ownerDao;
    private PostDao postDao;
    private PropertiesDao propertiesDao;
    private RegistrationDao registrationDao;
    private PropertiesImagesDao imagesDao;


    // == Used to get All the Posts ==
    @Override
    public List<Posts> getAllPost() {
        // == Getting all the posts and returning a list ==
        return createList(postDao.getAllPosts());
    }

    @Override
    public List<Posts> getOthersPost(String userId) {
        // == Getting all the posts and returning a list ==
        return createList(postDao.getAllOtherPost(userId));
    }

    @Override
    public List<Posts> getMyPost(String userId) {
        // == Getting all the posts and returning a list ==
        return createList(postDao.getMyPost(userId));
    }

    private List<Posts> createList(List<Post> postList){
        // == Creating a Empty list ==
        List<Posts> posts = new ArrayList<>();

        // == Looping though all pots ==
        for(Post post : postList){

            // == Creating new Model class ==
            Posts newPost = new Posts();

            // == Setting the values ==
            newPost.setPostId(post.getPostId());
            newPost.setOwner(ownerDao.getOwner(post.getOwnerId()));
            newPost.setProperty(propertiesDao.getProperty(post.getPropertyId()));
            newPost.setImage(getAllImages(imagesDao.getImage(post.getPropertyId())));
            newPost.setUser(registrationDao.getUser(post.getUserId()));

            // == Adding the post to the list ==
            posts.add(newPost);
        }

        // == Returning the Posts ==
        return posts;
    }

    // == Private methods ==
    private List<String> getAllImages(List<PropertiesImage> propertiesImages){

        // == Creating empty list ==
        List<String> images = new ArrayList<>();

        // == Looping though all the images ==
        for(PropertiesImage propertiesImage : propertiesImages){

            // == Converting images into Base64 ==
            images.add(getPicture(propertiesImage.getBlob()));
        }

        // == returning the list ==
        return images;
    }


    // == Setters ==

    @Autowired
    public void setOwnerDao(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setPropertiesDao(PropertiesDao propertiesDao) {
        this.propertiesDao = propertiesDao;
    }

    @Autowired
    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Autowired
    public void setImagesDao(PropertiesImagesDao imagesDao) {
        this.imagesDao = imagesDao;
    }
}
