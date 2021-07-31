package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {

    // == Public Methods ==
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {

        // == Creating the Model Class ==
        Post post = new Post();

        // == Setting the values ==
        post.setPostId(resultSet.getInt("post_id"));
        post.setUserId(resultSet.getInt("user_id"));
        post.setOwnerId(resultSet.getInt("owner_id"));
        post.setPropertyId(resultSet.getInt("property_id"));

        // == Returning the model ==
        return post;
    }
}
