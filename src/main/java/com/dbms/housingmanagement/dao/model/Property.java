package com.dbms.housingmanagement.dao.model;

import com.dbms.housingmanagement.services.model.PostInfo;
import lombok.Data;

@Data
public class Property {

    // == Fields ==
    private int propertyId;
    private String name;
    private String description;
    private int ownerId;
    private String cost;
    private String address;

    // == Creating Object using PropertyInfo ==
    public static Property createFromPropertyInfo(PostInfo postInfo, int ownerId){

        // == Creating a new Instance ==
        Property property = new Property();

        // == Setting the Values ==
        property.setName(postInfo.getPropertyName());
        property.setDescription(postInfo.getDetails());
        property.setCost(postInfo.getPrice());
        property.setOwnerId(ownerId);
        property.setAddress(postInfo.getAddress());

        // == Returning the Object ==
        return property;
    }
}
