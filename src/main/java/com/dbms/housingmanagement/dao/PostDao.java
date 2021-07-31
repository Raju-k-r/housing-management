package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.Post;

import java.util.List;

public interface PostDao {
    // == Get all Posts ==
    List<Post> getAllPosts();

    // == Get Post using postId ==
    Post getPost(int postId);

    // == Get post using propertyId ==
    Post getPost(String propertyId);

    // == Get all Property other then my post ==
    List<Post> getAllOtherPost(String userId);

    // == Get all my Property ==
    List<Post> getMyPost(String userId);

    // == Add new Post ==
    boolean addNewPost(Post post);

    // == Update Post ==
    boolean updatePost(Post post);

    // == Delete Post ==
    boolean deletePost(Post post);

    // == Delete Post ==
    boolean deletePost(int propertyId);
}
