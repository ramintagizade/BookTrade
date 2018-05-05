package com.ramin.Entity;

import java.util.List;

public class UserRequest {

    private String name;
    private List<TradeRequest> from;
    private List<TradeRequest> to;

    public UserRequest(String name, List <TradeRequest> from , List<TradeRequest> to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
