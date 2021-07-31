package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.mappers.PropertiesImageRowMapper;
import com.dbms.housingmanagement.dao.model.PropertiesImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PropertiesImagesDaoImpl implements PropertiesImagesDao {

    // == Fields ==
    private final JdbcTemplate jdbcTemplate;
    private final PropertiesImageRowMapper rowMapper;

    // == Constructor ==
    public PropertiesImagesDaoImpl(@Autowired DataSource dataSource) {
        // == Initializing the JdbcTemplate using datasource ==
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // == Initializing the PropertiesImageRowMapper ==
        rowMapper = new PropertiesImageRowMapper();
    }

    // == Public Methods ==


    // == Get all Properties Image ==
    @Override
    public List<PropertiesImage> getAllImages() {
        // == SQL query ==
        String query = "SELECT * FROM properties_images";

        // == Returning the all PropertiesImages ==
        return jdbcTemplate.query(query,rowMapper);

    }

    // == Get Properties using property id ==
    @Override
    public List<PropertiesImage> getImage(int propertyId) {
        // == SQL query ==
        String query = "SELECT * FROM properties_images WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ propertyId };

        // == Returning the all PropertiesImages ==
        return jdbcTemplate.query(query,args,rowMapper);
    }

    @Override
    public boolean addNewImage(PropertiesImage image) {
        // == SQL query ==
        String query = "INSERT INTO properties_images (property_id, image) VALUE (?,?)";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ image.getPropertyId(), image.getBlob() };

        // == Adding PropertiesImages and Returning the Result  ==
        return jdbcTemplate.update(query,args) == 1;
    }

    @Override
    public boolean updateProperty(PropertiesImage propertiesImage) {
        // == SQL query ==
        String query = "UPDATE properties_images SET image =? WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ propertiesImage.getBlob(), propertiesImage.getPropertyId() };

        // == Updating PropertiesImages and Returning the Result  ==
        return jdbcTemplate.update(query,args,rowMapper) == 1;
    }

    @Override
    public boolean deletePropertyImage(PropertiesImage propertiesImage) {
        // == SQL query ==
        String query = "DELETE FROM properties_images WHERE property_id = ?";

        // == Data Transfer Object ==
        Object[] args = new Object[]{ propertiesImage.getPropertyId() };

        // == Updating PropertiesImages and Returning the Result  ==
        return getImage(propertiesImage.getPropertyId()).size() == jdbcTemplate.update(query,args);
    }

    @Override
    public boolean deletePropertyImage(int propertyId) {
        return deletePropertyImage(getImage(propertyId).get(0));
    }
}
