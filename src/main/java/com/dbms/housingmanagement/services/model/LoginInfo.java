package com.dbms.housingmanagement.services.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginInfo {

    @NotBlank(message = "Email can't be blank")
    @Email
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;

}
