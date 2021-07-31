package com.dbms.housingmanagement.services.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostInfo {

    // == Fields ==
    @NotBlank(message = "Property Name Should not be Blank")
    private String propertyName;

    @NotBlank(message = "Address Should not be Blank")
    private String address;

    @NotBlank
    @Size(min = 10, message = "Details should be at list 10 Characters")
    private String details;

    @NotBlank
    private String price;

    @NotBlank
    private String ownerName;


    private String adharNumber;

    @NotBlank
    @Size(min = 10, max = 10, message = "Phone number should be Equal to 10 Characters")
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;
}
