package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.Registration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationRowMapper implements RowMapper<Registration> {

    // == Public Methods ==
    @Override
    public Registration mapRow(ResultSet resultSet, int i) throws SQLException {

        // == Creating a new Registration Model Class ==
        Registration registration = new Registration();

        // == Setting the Values ==
        registration.setUserId(resultSet.getInt("user_id"));
        registration.setFirstName(resultSet.getString("first_name"));
        registration.setLastName(resultSet.getString("last_name"));
        registration.setEmail(resultSet.getString("email"));
        registration.setPhoneNumber(resultSet.getString("phone_number"));
        registration.setAddress(resultSet.getString("address"));
        registration.setAdharNumber(resultSet.getString("adhar_number"));
        registration.setProfilePicture(resultSet.getBytes("profile_picture"));

        // == Returning the view ==
        return registration;
    }
}
