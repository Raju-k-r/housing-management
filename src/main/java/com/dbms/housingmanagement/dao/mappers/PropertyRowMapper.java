package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.Property;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<Property> {

    // == Public Methods ==
    @Override
    public Property mapRow(ResultSet resultSet, int i) throws SQLException {

        // == Creating the model ==
        Property property = new Property();

        // == Setting the values ==
        property.setPropertyId(resultSet.getInt("property_id"));
        property.setName(resultSet.getString("name"));
        property.setDescription(resultSet.getString("description"));
        property.setOwnerId(resultSet.getInt("owner_id"));
        property.setCost(resultSet.getString("cost"));
        property.setAddress(resultSet.getString("address"));

        // == returning the Model ==
        return property;
    }
}
