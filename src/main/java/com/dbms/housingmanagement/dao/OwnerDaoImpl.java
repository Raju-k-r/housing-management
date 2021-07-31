package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.OwnerRowMapper;
import com.dbms.housingmanagement.dao.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class OwnerDaoImpl implements OwnerDao{

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final OwnerRowMapper rowMapper;

    // == Constructor ==
    public OwnerDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the jdbcTemplate using dataSource==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing Row mapper ==
        this.rowMapper = new OwnerRowMapper();
    }

    // == Public Methods ==

    // == Get all Owner and returning the List of Owners==
    @Override
    public List<Owner> getAllOwners() {
        // == SQL Query ==
        String query = "SELECT * FROM owners";

        // == Fetching all owners from database and returning==
        return jdbcTemplate.query(query,rowMapper);
    }

    // == Get the user based on the email ==
    @Override
    public Owner getOwner(String email) {
        // == SQL Query ==
        String query = "SELECT * FROM owners WHERE email=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ email };

        // == Fetching owner data and Returning it ==
        try{
            Owner data =  jdbcTemplate.queryForObject(query,rowMapper,args);
            log.info("Data From Database-> {}", data);
            return data;
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    // == Get the user based on the email ==
    @Override
    public Owner getOwner(int ownerId) {
        // == SQL Query ==
        String query = "SELECT * FROM owners WHERE owner_id=?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ ownerId };

        // == Fetching owner data and Returning it ==
        try{
            Owner data =  jdbcTemplate.queryForObject(query,rowMapper,args);
            log.info("Data From Database-> {}", data);
            return data;
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    // == Adding new Owner ==
    @Override
    public boolean addNewOwner(Owner owner) {
        // == SQL Query ==
        String query = "INSERT INTO owners (name, phone_number, email) VALUE (?,?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ owner.getName(),  owner.getPhoneNumber(), owner.getEmail() };

        // == Adding the Owner and retuning result ==
        return jdbcTemplate.update(query,args) == 1;
    }

    // == Updating the Owner ==
    @Override
    public boolean updateOwner(Owner owner) {
        // == SQL Query ==
        String query = "UPDATE owners SET name=?, phone_number=?, email=? WHERE owner_id =?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{owner.getName(), owner.getPhoneNumber(), owner.getEmail(), owner.getOwnerId()};

        // == Updating Owner and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    // == Deleting the Owner ==
    @Override
    public boolean deleteOwner(Owner owner) {
        // == SQL Query ==
        String query = "DELETE FROM owners WHERE owner_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ owner.getOwnerId() };

        // == Deleting owner and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

}
