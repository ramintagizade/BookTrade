package com.ramin.Dao;

import com.ramin.Entity.User;
import com.ramin.Entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String ... args) {

    }

    public String findPasswordById(String id) {
        return this.userRepository.findById(id).get().getPassword();
    }

    public void registerUser(User user) {
        this.userRepository.save(user);
    }

}
