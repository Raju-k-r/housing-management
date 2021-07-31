package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRowMapper implements RowMapper<Owner> {

    // == Public methods ==
    @Override
    public Owner mapRow(ResultSet resultSet, int i) throws SQLException {

        // == Creating the model class ==
        Owner owner = new Owner();

        // == Setting the values ==
        owner.setOwnerId(resultSet.getInt("owner_id"));
        owner.setName(resultSet.getString("name"));
        owner.setPhoneNumber(resultSet.getString("phone_number"));
        owner.setEmail(resultSet.getString("email"));

        // == returning the model ==
        return owner;
    }
}
