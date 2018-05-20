package com.ramin.Dao;

import com.ramin.Entity.Token;
import com.ramin.Entity.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenDao implements CommandLineRunner {

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void run(String ... args) {

    }

    public Optional<Token> getToken() {
        return this.tokenRepository.findById("token");
    }
}
