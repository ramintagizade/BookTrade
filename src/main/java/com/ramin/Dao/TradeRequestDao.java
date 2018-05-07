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

        List<TradeRequest> sendRequests = userRequestSender.get().getFrom();
        sendRequests.add(tradeRequest);
        userRequestSender.get().setFrom(sendRequests);

        List<TradeRequest> receiverRequests = userRequestReceiver.get().getTo();
        receiverRequests.add(tradeRequest);
        userRequestReceiver.get().setTo(receiverRequests);

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

        List<TradeRequest> sendRequests = userRequestSender.get().getFrom();
        sendRequests.remove(tradeRequest);
        userRequestSender.get().setFrom(sendRequests);

        List<TradeRequest> receiverRequests = userRequestReceiver.get().getTo();
        receiverRequests.remove(tradeRequest);
        userRequestReceiver.get().setTo(receiverRequests);

        updateUserRequests(userRequestSender);
        updateUserRequests(userRequestReceiver);
    }

    public void acceptTradeRequest() {

    }

    public void rejectTradeRequest() {

    }
}
