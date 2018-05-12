package com.ramin.Entity;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;

public class User {
    @Id
    private String id;
    private String password;

    public User(String email, String password) {
        this.id = email;
        this.password = password;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String email) {
        this.id = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
