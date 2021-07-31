package com.dbms.housingmanagement.services.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Data
public class RegistrationInfo {

    @NotBlank
    @Size(min = 3, message = "Firstname should be more then or equal to 3")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Email Can't be blank")
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank(message = "Phone number can't be blank")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Invalid phone number")
    private String phoneNumber;

    @Size(min = 12, max = 12, message = "Adhar Number Should be equal to 12 digits")
    private String adharNumber;

    @NotBlank
    @Size(min = 6, message = "Password Should be at least 6 Characters")
    private String password;

    private String confirmPassword;

    @Nullable
    private byte[] profilePicture;

}
