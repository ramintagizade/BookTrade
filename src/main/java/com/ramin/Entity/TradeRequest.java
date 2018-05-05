package com.ramin.Entity;



public class TradeRequest {

    private String user;
    private String bookId;

    public TradeRequest(String user, String bookId ) {
        this.user = user;
        this.bookId = bookId;
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
