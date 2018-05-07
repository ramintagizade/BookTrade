package com.ramin.Entity;


import org.springframework.data.annotation.Id;

import java.util.UUID;

public class TradeRequest {

    @Id
    private String id;
    private String user;
    private String bookId;

    public TradeRequest(String user, String bookId ) {
        this.user = user;
        this.bookId = bookId;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
