package com.dbms.housingmanagement.dao.model;

import com.dbms.housingmanagement.services.model.PostInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Owner {

    // == Fields ==
    private int ownerId;

    @EqualsAndHashCode.Include
    private String name;
    private String phoneNumber;

    @EqualsAndHashCode.Include
    private String email;

    // == Creating new Owner Object using PostInfo ==
    public static Owner createObject(PostInfo postInfo){
        // == Creating new object ==
        Owner owner = new Owner();

        // == Setting values ==
        owner.setName(postInfo.getOwnerName());
        owner.setPhoneNumber(postInfo.getPhoneNumber());
        owner.setEmail(postInfo.getEmail());

        // == returning the object ==
        return owner;
    }
}
