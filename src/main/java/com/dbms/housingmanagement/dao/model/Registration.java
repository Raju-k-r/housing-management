package com.dbms.housingmanagement.dao.model;

import com.dbms.housingmanagement.services.model.RegistrationInfo;
import lombok.Data;

@Data
public class Registration {

    // == Fields ==
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String adharNumber;
    private byte[] profilePicture;

    // == Creating the Registration Object using RegistrationInfo Object ==
    public static Registration createRegistration(RegistrationInfo registrationInfo){
        // == Creating new Instance ==
        Registration registration = new Registration();

        // == Setting the values ==
        registration.setFirstName(registrationInfo.getFirstName());
        registration.setLastName(registrationInfo.getLastName());
        registration.setEmail(registrationInfo.getEmail());
        registration.setAddress(registrationInfo.getAddress());
        registration.setPhoneNumber(registrationInfo.getPhoneNumber());

        if(registrationInfo.getAdharNumber() == null){
            registration.setAdharNumber("");
        }else{
            registration.setAdharNumber(registrationInfo.getAdharNumber());
        }
        registration.setProfilePicture(registrationInfo.getProfilePicture());
        // == Returning the Object ==
        return registration;
    }
}
