package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.PropertiesImage;

import java.util.List;

public interface PropertiesImagesDao {

    // == Get all Properties Image ==
    List<PropertiesImage> getAllImages();

    // == Get Properties using property id ==
    List<PropertiesImage> getImage(int propertyId);

    // == Add new Property Image ==
    boolean addNewImage(PropertiesImage image);

    // == Update Property Image==
    boolean updateProperty(PropertiesImage propertiesImage);

    // == Delete Property Image ==
    boolean deletePropertyImage(PropertiesImage propertiesImage);

    // == Delete Property Image ==
    boolean deletePropertyImage(int propertyId);
}
