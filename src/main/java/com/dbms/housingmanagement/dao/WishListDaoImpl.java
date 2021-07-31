package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.WishListRowMapper;
import com.dbms.housingmanagement.dao.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WishListDaoImpl implements WishListDao{

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final WishListRowMapper rowMapper;

    // == Constructor ==
    public WishListDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the Jdbc template ==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing the Row Mapper ==
        this.rowMapper = new WishListRowMapper();
    }

    // == Adding the wishlist for the user ==
    @Override
    public boolean addWishList(int userId, int propertyId) {
        // == SQL Query ==
        String query = "INSERT INTO wish_list (user_id, property_id) VALUE (?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ userId, propertyId };

        // == Adding wishlist and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    // == Getting wishlist of user ==
    @Override
    public List<WishList> getAllWishList(int userId) {
        // == SQL Query ==
        String query = "SELECT * FROM wish_list WHERE user_id=? ORDER BY sl_no DESC";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ userId };

        // == Fetching Wishlist and Returning it ==
        try{
            return jdbcTemplate.query(query,rowMapper,args);
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    @Override
    public List<WishList> isPropertyInWishList(int userId, int propertyId) {
        // == SQL Query ==
        String query = "SELECT * FROM wish_list WHERE user_id = ? AND property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ userId, propertyId };

        // == Fetching wishlist and returning result ==
        return jdbcTemplate.query(query, rowMapper, args);
    }

    @Override
    public boolean removeTheWishList(int propertyId, int userId) {
        // == SQL Query ==
        String query = "DELETE FROM wish_list WHERE user_id = ? AND property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ userId, propertyId };

        // == Fetching wishlist and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    @Override
    public boolean removeTheWishList(int propertyId) {
        // == SQL Query ==
        String query = "DELETE FROM wish_list WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ propertyId };

        // == Fetching wishlist and returning result ==
        return jdbcTemplate.update(query, args) == getAllWishList(Integer.toString(propertyId)).size();
    }

    @Override
    public List<WishList> getAllWishList(String propertyId) {
        // == SQL Query ==
        String query = "SELECT * FROM wish_list WHERE property_id=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ String.valueOf(propertyId) };

        // == Fetching Wishlist and Returning it ==
        try{
            return jdbcTemplate.query(query,rowMapper,args);
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }
}
