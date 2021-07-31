package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishListRowMapper implements RowMapper<WishList> {

    // == Public Methods ==
    @Override
    public WishList mapRow(ResultSet resultSet, int i) throws SQLException {
        // == Creating a Model class ==
        WishList wishList = new WishList();

        // == Setting the Values ==
        wishList.setUserId(resultSet.getInt("user_id"));
        wishList.setPropertyId(resultSet.getInt("property_id"));

        // == Returning the model class ==
        return wishList;
    }
}
