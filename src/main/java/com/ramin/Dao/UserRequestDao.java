package com.ramin.Dao;

import com.ramin.Entity.UserRequest;
import com.ramin.Entity.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRequestDao implements CommandLineRunner {

    @Autowired
    UserRequestRepository userRequestRepository;

    @Override
    public void run(String ... args) {

    }

    public Optional<UserRequest> getUserRequests(String email) {
        return this.userRequestRepository.findById(email);
    }

    public void updateUserRequests(UserRequest userRequest) {
        this.userRequestRepository.save(userRequest);
    }


}
