package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.PostRowMapper;
import com.dbms.housingmanagement.dao.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class PostDaoImpl implements PostDao{

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final PostRowMapper rowMapper;

    // == Constructor ==
    public PostDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the jdbcTemplate using dataSource==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing Row mapper ==
        this.rowMapper = new PostRowMapper();
    }

    // == Public Methods ==

    // == Get all Post and returning the List of Post==
    @Override
    public List<Post> getAllPosts() {
        // == SQL Query ==
        String query = "SELECT * FROM posts ORDER BY post_id DESC";

        // == Fetching all Login from database and returning==
        return jdbcTemplate.query(query,rowMapper);

    }

    // == Get post based on postId ==
    @Override
    public Post getPost(int postId) {
        // == SQL Query ==
        String query = "SELECT * FROM posts WHERE post_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ postId };

        // == Fetching post and Returning it ==
        try{
            Post data =  jdbcTemplate.queryForObject(query,rowMapper,args);
            log.info("Data From Database-> {}", data);
            return data;
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    @Override
    public Post getPost(String propertyId) {
        // == SQL Query ==
        String query = "SELECT * FROM posts WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ Integer.parseInt(propertyId) };

        // == Fetching post and Returning it ==
        try{
            Post data =  jdbcTemplate.queryForObject(query,rowMapper,args);
            log.info("Data From Database-> {}", data);
            return data;
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    @Override
    public List<Post> getAllOtherPost(String userId) {
        // == SQL Query ==
        String query = "SELECT * FROM posts WHERE user_id <> ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ Integer.parseInt(userId) };

        // == Fetching post and Returning it ==
        return jdbcTemplate.query(query,args,rowMapper);
    }

    @Override
    public List<Post> getMyPost(String userId) {
        // == SQL Query ==
        String query = "SELECT * FROM posts WHERE user_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ Integer.parseInt(userId) };

        // == Fetching post and Returning it ==
        return jdbcTemplate.query(query,args,rowMapper);
    }

    // == Adding new Post ==
    @Override
    public boolean addNewPost(Post post) {
        // == SQL Query ==
        String query = "INSERT INTO posts (user_id, owner_id, property_id) VALUE (?,?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ post.getUserId(), post.getOwnerId(), post.getPropertyId() };

        // == Adding the Post and retuning result ==
        return jdbcTemplate.update(query,args) == 1;
    }

    // == Updating The post ==
    @Override
    public boolean updatePost(Post post) {
        // == SQL Query ==
        String query = "UPDATE posts SET user_id = ?, owner_id = ?, property_id = ? WHERE post_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ post.getUserId(), post.getOwnerId(), post.getPropertyId(), post.getPostId() };

        // == Updating Post and returning result ==
        return jdbcTemplate.update(query, args) == 1;

    }

    // == Deleting the Post ==
    @Override
    public boolean deletePost(Post post) {
        // == SQL Query ==
        String query = "DELETE FROM posts WHERE post_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ post.getPostId() };

        // == Deleting Post and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    @Override
    public boolean deletePost(int propertyId) {
        return deletePost(getPost(String.valueOf(propertyId)));
    }
}
