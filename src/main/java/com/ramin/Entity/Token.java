package com.ramin.Entity;

import org.springframework.data.annotation.Id;

public class Token {

    @Id
    private String id;


    public Token(String id ){
        this.id = id;
    }

    public String getToken() {
        return id;
    }

    public void setToken(String id) {
        this.id = id;
    }
}
