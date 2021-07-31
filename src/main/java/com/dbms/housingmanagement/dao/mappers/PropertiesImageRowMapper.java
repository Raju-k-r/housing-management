package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.PropertiesImage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertiesImageRowMapper implements RowMapper<PropertiesImage> {

    // == Public Methods ==
    @Override
    public PropertiesImage mapRow(ResultSet resultSet, int i) throws SQLException {
        // == Creating new Model class ==
        PropertiesImage image = new PropertiesImage();

        // == Setting the Values ==
        image.setSerialNumber(resultSet.getInt("sl_no"));
        image.setPropertyId(resultSet.getInt("property_id"));
        image.setBlob(resultSet.getBytes("image"));

        // == Returning the Model class ==
        return image;
    }
}
