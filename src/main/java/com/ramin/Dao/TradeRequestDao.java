package com.ramin.Dao;

import com.ramin.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TradeRequestDao extends UserRequestDao implements CommandLineRunner {

    @Autowired
    TradeRequestRepository tradeRequestRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String ... args) {

    }

    public Optional<TradeRequest> getTradeRequestById(String id) {
        return this.tradeRequestRepository.findById(id);
    }

    public void insertTradeRequest(TradeRequest tradeRequest) {
        this.tradeRequestRepository.insert(tradeRequest);

        Optional<UserRequest> userRequestSender = getUserRequests(tradeRequest.getUser());
        Optional<UserRequest> userRequestReceiver = getUserRequests(getBookOwnerById(tradeRequest.getBookId()));

        userRequestSender.get().addFrom(tradeRequest);
        userRequestReceiver.get().addTo(tradeRequest);

        updateUserRequests(userRequestSender);
        updateUserRequests(userRequestReceiver);
    }

    public String getBookOwnerById(String id) {
        return this.bookRepository.findById(id).get().getOwner();
    }

    public void removeTradeRequestById(String id) {
        Optional<TradeRequest> tradeRequest = getTradeRequestById(id);
        this.tradeRequestRepository.deleteById(id);

        Optional<UserRequest> userRequestSender = getUserRequests(tradeRequest.get().getUser());
        Optional<UserRequest> userRequestReceiver = getUserRequests(getBookOwnerById(tradeRequest.get().getBookId()));

        userRequestSender.get().removeFrom(tradeRequest.get());
        userRequestReceiver.get().removeTo(tradeRequest.get());

        updateUserRequests(userRequestSender);
        updateUserRequests(userRequestReceiver);
    }

    public void acceptTradeRequestById(String id) {
        TradeRequest tradeRequest = getTradeRequestById(id).get();
        tradeRequest.setAccepted(true);
        updateTradeRequest(tradeRequest);
    }

    public void rejectTradeRequestById(String id) {
        TradeRequest tradeRequest = getTradeRequestById(id).get();
        tradeRequest.setRejected(false);
        updateTradeRequest(tradeRequest);
    }

    public void updateTradeRequest(TradeRequest tradeRequest) {
        this.tradeRequestRepository.save(tradeRequest);
    }
}
