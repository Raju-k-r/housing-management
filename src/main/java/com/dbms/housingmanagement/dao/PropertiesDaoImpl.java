package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.PropertyRowMapper;
import com.dbms.housingmanagement.dao.model.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class PropertiesDaoImpl implements PropertiesDao{

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final PropertyRowMapper rowMapper;

    // == Constructor ==
    public PropertiesDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the JdbcTemplate using datasource ==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing the PropertyRowMapper ==
        rowMapper = new PropertyRowMapper();
    }

    // == Public Methods ==

    // == Getting all Property ==
    @Override
    public List<Property> getAllProperties() {
        // == SQL query ==
        String query = "SELECT * FROM properties";

        // == Returning the Property ==
        return jdbcTemplate.query(query,rowMapper);
    }

    // == Get Property based on id ==
    @Override
    public Property getProperty(int propertyId) {
        // == SQL Query ==
        String query = "SELECT * FROM properties WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ propertyId };

        // == Fetching Property and Returning it ==
        try{
            return jdbcTemplate.queryForObject(query,rowMapper,args);
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }
    }

    // == Adding new Property ==
    @Override
    public boolean addNewProperty(Property property) {
        // == SQL Query ==
        String query = "INSERT INTO properties (name, description, owner_id, cost, address) VALUE (?,?,?,?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ property.getName(), property.getDescription(), property.getOwnerId(), property.getCost(), property.getAddress() };

        // == Inserting Property and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    // == Updating new Property ==
    @Override
    public boolean updateProperty(Property property) {
        // == SQL Query ==
        String query = "UPDATE properties SET name = ?, description = ?, owner_id = ?, cost = ?, address=? WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ property.getName(), property.getDescription(), property.getOwnerId(), property.getCost(), property.getAddress(), property.getPropertyId() };

        // == Updating Property and returning result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    // == Deleting the Property ==
    @Override
    public boolean deleteProperty(Property property) {
        // == SQL Query ==
        String query = "DELETE FROM properties WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ property.getPropertyId() };

        // == Deleting the Property and returning the result ==
        return jdbcTemplate.update(query, args) == 1;
    }

    @Override
    public boolean deleteProperty(int propertyId) {
        return deleteProperty(getProperty(propertyId));
    }

    // == Getting Property using Property ==
    @Override
    public Property getProperty(Property property) {
        // == SQL Query ==
        String query = "SELECT * FROM properties WHERE name = ? AND description = ? AND cost = ? AND owner_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ property.getName(), property.getDescription(), property.getCost(), property.getOwnerId() };

        // == Fetching Property and Returning it ==
        try{
            return jdbcTemplate.queryForObject(query,rowMapper,args);
        }catch (EmptyResultDataAccessException e){
            // == If no data found returning null ==
            return null;
        }

    }
}
