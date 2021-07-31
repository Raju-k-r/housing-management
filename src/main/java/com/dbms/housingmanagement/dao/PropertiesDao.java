package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.Property;

import java.util.List;

public interface PropertiesDao {

    // == Get all Property ==
    List<Property> getAllProperties();

    // == Get Property using propertyId ==
    Property getProperty(int propertyId);

    // == Get Property using Property ==
    Property getProperty(Property property);

    // == Add new Property ==
    boolean addNewProperty(Property property);

    // == Update Property ==
    boolean updateProperty(Property property);

    // == Delete Property ==
    boolean deleteProperty(Property property);

    // == Delete the property bases on id ==
    boolean deleteProperty(int propertyId);

}
