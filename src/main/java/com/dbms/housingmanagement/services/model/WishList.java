package com.dbms.housingmanagement.services.model;

import com.dbms.housingmanagement.dao.model.Owner;
import com.dbms.housingmanagement.dao.model.Property;
import lombok.Data;

@Data
public class WishList {

    // == Fields ==
    private Owner owner;
    private Property property;
    private String propertyImage;
}
