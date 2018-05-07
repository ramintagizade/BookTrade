package com.ramin.Entity;

import java.util.List;

public class UserRequest {

    private String email;
    private List<TradeRequest> from;
    private List<TradeRequest> to;

    public UserRequest(String email, List <TradeRequest> from , List<TradeRequest> to) {
        this.email = email;
        this.from = from;
        this.to = to;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.email = email;
    }

    public List<TradeRequest> getFrom() {
        return from;
    }

    public void setFrom(List<TradeRequest> from) {
        this.from = from;
    }

    public List<TradeRequest> getTo() {
        return to;
    }

    public void setTo(List<TradeRequest> to) {
        this.to = to;
    }
}
