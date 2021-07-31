package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.RegistrationRowMapper;
import com.dbms.housingmanagement.dao.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final RegistrationRowMapper rowMapper;

    // == Constructor ==
    public RegistrationDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the JdbcTemplate using datasource ==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing the RegistrationRowMapper ==
        rowMapper = new RegistrationRowMapper();
    }

    // == Public Methods ==

    // == Getting all User ==
    @Override
    public List<Registration> getAllRegisteredUser() {
        // == SQL query ==
        String query = "SELECT * FROM users";

        // == Returning the all Users ==
        return jdbcTemplate.query(query,rowMapper);
    }

    // == Get the user bases on the email ==
    @Override
    public Registration getUser(String email) {
        // == SQL Query ==
        String query = "SELECT * FROM users WHERE email=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ email };

        // == Fetching User and Returning it ==
        try{
            return jdbcTemplate.queryForObject(query,rowMapper,args);
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    // == Get the user bases on the userId ==
    @Override
    public Registration getUser(int userId) {
        // == SQL Query ==
        String query = "SELECT * FROM users WHERE user_id=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ userId };

        // == Fetching User and Returning it ==
        try{
            return jdbcTemplate.queryForObject(query,rowMapper,args);
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    // == Updating the user ==
    @Override
    public boolean updateUser(Registration registration) {
        // == SQL Query ==
        String query = "UPDATE users SET first_name=?, last_name=?, address=?, phone_number=?, adhar_number=? WHERE email=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ registration.getFirstName(), registration.getLastName(), registration.getAddress(),
                registration.getPhoneNumber() ,registration.getAdharNumber(), registration.getEmail()};

        // == Updating user and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    // == Adding the new User ==
    @Override
    public boolean addNewUser(Registration registration) {
        // == SQL Query ==
        String query = "INSERT INTO users (first_name, last_name, email, phone_number, address, adhar_number, profile_picture) VALUE (?,?,?,?,?,?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ registration.getFirstName(), registration.getLastName(), registration.getEmail(), registration.getPhoneNumber(), registration.getAddress(), registration.getAdharNumber(), registration.getProfilePicture() };

        // == Adding the user and returning the result ==
        return jdbcTemplate.update(query,args) == 1;
    }

    // == Deleting the user ==
    @Override
    public boolean deleteUser(Registration registration) {
        // == SQL Query ==
        String query = "DELETE FROM users WHERE user_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ registration.getUserId() };

        // == Deleting the user and returning the result ==
        return jdbcTemplate.update(query,rowMapper, args) == 1;
    }

    // == Getting the userId based on email ==
    @Override
    public int getUserId(String email) {
        // == Getting the User and returning the userId==
        return getUser(email).getUserId();
    }
}
