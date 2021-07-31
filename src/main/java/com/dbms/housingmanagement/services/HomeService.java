package com.dbms.housingmanagement.services;

import com.dbms.housingmanagement.services.model.Posts;
import org.springframework.security.crypto.codec.Base64;

import java.nio.charset.StandardCharsets;
import java.util.List;

public interface HomeService {


    // == Used to convert image to Base64 ==
    default String getPicture(byte[] profilePicture){
        return new String(Base64.encode(profilePicture), StandardCharsets.UTF_8);
    }

    // == Get all posts ==
    List<Posts> getAllPost();

    // == Get Others Posts ==
    List<Posts> getOthersPost(String userId);

    // == Get My Posts ==
    List<Posts> getMyPost(String userId);

}
