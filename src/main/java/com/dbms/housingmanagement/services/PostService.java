package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.dao.model.Registration;
import com.dbms.housingmanagement.services.model.PostInfo;

import java.util.List;

public interface PostService {

    // == Get new Post Form ==
    PostInfo getNewPostForm();

    // == Add new Post ==
    boolean addNewPost(PostInfo postInfo, Registration user, List<byte[]> images) throws Exception;

    // == Adding Image to Property ==
    boolean addNewImage(byte[] image, int propertyId);

    // == Delete the Post ==
    boolean deletePost(int propertyId);
}
