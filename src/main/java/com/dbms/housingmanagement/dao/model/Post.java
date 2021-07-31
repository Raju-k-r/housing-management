package com.dbms.housingmanagement.dao.model;

import lombok.Data;

@Data
public class Post {

    // == Fields ==
    private int postId;
    private int userId;
    private int ownerId;
    private int propertyId;


    // == Creating the Post Object ==
    public static Post createObject(int userId, int ownerId, int propertyId){
        // == Creating the Post ==
        Post post = new Post();

        // == Setting the Values ==
        post.setUserId(userId);
        post.setOwnerId(ownerId);
        post.setPropertyId(propertyId);

        // == Returning the Post ==
        return post;
    }

}
