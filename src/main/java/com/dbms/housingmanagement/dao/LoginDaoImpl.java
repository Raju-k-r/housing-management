package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.LoginRowMapper;
import com.dbms.housingmanagement.dao.model.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class LoginDaoImpl implements LoginDao{

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Login> rowMapper;

    // == Constructor ==
    public LoginDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the jdbcTemplate using dataSource==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing Row mapper ==
        this.rowMapper = new LoginRowMapper();

    }

    // == Public Methods ==

    // == Get all Login and returning the List of Login==
    @Override
    public List<Login> getAllLogins() {

        // == SQL Query ==
        String query = "SELECT * FROM login";

        // == Fetching all Login from database and returning==
        return jdbcTemplate.query(query,rowMapper);
    }

    // == Get Login based on email ==
    @Override
    public Login getLogin(String email) {
        // == SQL Query ==
        String query = "SELECT * FROM login WHERE email=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ email };

        // == Fetching Login data and Returning it ==
        try{
            Login data =  jdbcTemplate.queryForObject(query,rowMapper,args);
            log.info("Data From Database-> {}", data);
            return data;
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    // == Adding New Login ==
    @Override
    public boolean addNewLogin(Login login) {
        // == SQL Query ==
        String query = "INSERT INTO login (user_id, name, email, password,user_type) VALUE (?,?,?,?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ login.getUserId(),login.getName(), login.getEmail(), login.getPassword(), login.getUserType() };

        // == Adding the Login Data and retuning result ==
        return jdbcTemplate.update(query,args) == 1;
    }

    // == Updating the Login Data ==
    @Override
    public boolean updateLogin(Login login) {
        // == SQL Query ==
        String query = "UPDATE login SET user_id=?, name = ?, email=?, password = ? WHERE user_id =?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{login.getUserId(), login.getName(), login.getEmail(), login.getPassword(), login.getUserId()};

        // == Updating login and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    // == Deleting the Login Data ==
    @Override
    public boolean deleteLogin(Login login) {
        // == SQL Query ==
        String query = "DELETE FROM login WHERE user_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ login.getUserId()};

        // == Deleting login and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }
}

