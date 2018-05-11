package com.ramin.Entity;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class UserRequest {

    @Id
    private String id;
    private List<TradeRequest> from;
    private List<TradeRequest> to;

    public UserRequest(String email, List <TradeRequest> from , List<TradeRequest> to) {
        this.id = email;
        this.from = from;
        this.to = to;
    }

    public UserRequest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String email) {
        this.id = email;
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

    public void addFrom(TradeRequest tradeRequest) {
        this.from.add(tradeRequest);
    }

    public void removeFrom(TradeRequest tradeRequest) {

        Iterator<TradeRequest> i = this.from.iterator();
        while (i.hasNext()) {
            TradeRequest o = i.next();
            if(o.getBookId().equals(tradeRequest.getBookId())) {
                i.remove();
                break;
            }
        }
    }

    public void addTo(TradeRequest tradeRequest) {
        this.to.add(tradeRequest);
    }

    public void removeTo(TradeRequest tradeRequest) {
        Iterator<TradeRequest> i = this.to.iterator();
        while (i.hasNext()) {
            TradeRequest o = i.next();
            if(o.getBookId().equals(tradeRequest.getBookId())) {
                i.remove();
                break;
            }
        }
    }

    public void updateTradeFrom(TradeRequest tradeRequest) {
        Iterator<TradeRequest> i = this.from.iterator();
        while(i.hasNext()) {
            TradeRequest o = i.next();
            if(o.getBookId().equals(tradeRequest.getBookId())) {
                o.setAccepted(tradeRequest.getAccepted());
                o.setRejected(tradeRequest.getRejected());
                break;
            }
        }
    }

    public void updateTradeTo(TradeRequest tradeRequest) {
        Iterator<TradeRequest> i = this.to.iterator();
        while(i.hasNext()) {
            TradeRequest o = i.next();
            if(o.getBookId().equals(tradeRequest.getBookId())) {
                o.setAccepted(tradeRequest.getAccepted());
                o.setRejected(tradeRequest.getRejected());
                break;
            }
        }
    }


    public String toString() {
        return "[" + this.id + ", from: " + this.from + ", to : " + this.to + "]";
    }
}
