package com.dbms.housingmanagement.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class Login {

    // == Fields ==
    private int userId;
    private String name;
    private String email;
    private String password;
    private String userType;

    // == Constructor ==
    public Login(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.userType = "USER";
    }
}
