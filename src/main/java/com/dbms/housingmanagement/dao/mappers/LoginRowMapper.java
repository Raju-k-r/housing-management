package com.dbms.housingmanagement.dao.mappers;

import com.dbms.housingmanagement.dao.model.Login;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRowMapper implements RowMapper<Login> {

    // == Public Methods ==
    @Override
    public Login mapRow(ResultSet resultSet, int i) throws SQLException {

        // == Creating new Login model class ==
        Login login = new Login();

        // == Setting the values ==
        login.setUserId(resultSet.getInt("user_id"));
        login.setName(resultSet.getString("name"));
        login.setEmail(resultSet.getString("email"));
        login.setPassword(resultSet.getString("password"));
        login.setUserType(resultSet.getString("user_type"));

        // == Returning the view ==
        return login;
    }
}
