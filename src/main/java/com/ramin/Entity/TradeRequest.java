package com.ramin.Entity;


import org.springframework.data.annotation.Id;

import java.util.UUID;

public class TradeRequest {

    @Id
    private String id;
    private String user;
    private String bookId;
    private Boolean accepted;
    private Boolean rejected;

    public TradeRequest(String user, String bookId ) {
        this.user = user;
        this.bookId = bookId;
        this.id = UUID.randomUUID().toString();
        this.accepted = false;
        this.rejected = false;
    }

    public TradeRequest() {

    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
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

    public String toString() {
        return "[ " +this.user + ", " + this.bookId+", " +this.accepted +", "+ ", "+ this.rejected+ ", " +this.id +" ]";
    }
}
