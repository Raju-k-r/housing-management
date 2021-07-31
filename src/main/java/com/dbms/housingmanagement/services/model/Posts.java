package com.dbms.housingmanagement.services.model;

import com.dbms.housingmanagement.dao.model.Owner;
import com.dbms.housingmanagement.dao.model.Property;
import com.dbms.housingmanagement.dao.model.Registration;
import lombok.Data;

import java.util.List;

@Data
public class Posts {

    // == Fields ==
    private int postId;
    private Registration user;
    private Owner owner;
    private Property property;
    private List<String> image;
}
