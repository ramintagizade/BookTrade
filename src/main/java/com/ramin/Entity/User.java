package com.ramin.Entity;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;

public class User {
    @Id
    private String id;
    private String password;
    private String username;

    public User(String username, String email, String password) {
        this.id = email;
        this.password = password;
        this.username = username;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
