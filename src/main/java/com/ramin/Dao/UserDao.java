package com.ramin.Dao;

import com.ramin.Entity.User;
import com.ramin.Entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public boolean registerUser(User user) {
        if(!findUserById(user.getId()).isPresent()){
            this.userRepository.save(user);
            return true;
        }
        else {
            return false;
        }
    }

    public Optional<User> findUserById(String id) {
        return this.userRepository.findById(id);
    }
}
