package com.dbms.housingmanagement.dao;

import com.dbms.housingmanagement.dao.model.Owner;

import java.util.List;

public interface OwnerDao {

    // == Get all Owners ==
    List<Owner> getAllOwners();

    // == Get Owner using email ==
    Owner getOwner(String email);

    // == Get Owner using Id ==
    Owner getOwner(int ownerId);

    // == Add new Owner ==
    boolean addNewOwner(Owner owner);

    // == Update Owner ==
    boolean updateOwner(Owner owner);

    // == Delete Owner ==
    boolean deleteOwner(Owner owner);
}
