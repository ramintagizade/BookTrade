package com.ramin.Dao;

import com.ramin.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
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
        tradeRequest = new TradeRequest(tradeRequest.getUser(),tradeRequest.getBookId());
        this.tradeRequestRepository.insert(tradeRequest);

        Optional <UserRequest> userRequestSender = getUserRequests(tradeRequest.getUser());
        Optional <UserRequest> userRequestReceiver = getUserRequests(getBookOwnerById(tradeRequest.getBookId()));

        if(userRequestSender.isPresent()) {
            userRequestSender.get().addFrom(tradeRequest);
        }
        else {
            List <TradeRequest> from = Collections.singletonList(tradeRequest);
            userRequestSender = Optional.of(new UserRequest(tradeRequest.getUser(), Collections.emptyList(), Collections.emptyList()));
            userRequestSender.get().setFrom(from);
        }
        if(userRequestReceiver.isPresent()) {
            userRequestReceiver.get().addTo(tradeRequest);
        }
        else {
            List <TradeRequest> to = Collections.singletonList(tradeRequest);
            userRequestReceiver = Optional.of(new UserRequest(getBookOwnerById(tradeRequest.getBookId()), Collections.emptyList(), Collections.emptyList()));
            userRequestReceiver.get().setTo(to);
        }
        updateUserRequests(userRequestSender.get());
        updateUserRequests(userRequestReceiver.get());
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

        updateUserRequests(userRequestSender.get());
        updateUserRequests(userRequestReceiver.get());
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
