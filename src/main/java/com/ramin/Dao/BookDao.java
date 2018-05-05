package com.ramin.Dao;

import com.mongodb.BasicDBObject;
import com.ramin.Entity.Book;
import com.ramin.Entity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.*;

@Component
public class BookDao implements CommandLineRunner {


    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String ... args) {
        //bookRepository.insert(new Book("Java EE","Jessy","stackoverflow.com"));
        System.out.println(bookRepository.findAll().toString());
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Collection<Book> getMyBooks(String owner) {
        Collection<Book> myBooks = this.getAllBooks().stream()
                .filter(p -> p.getOwner().equals(owner) ).collect(Collectors.toList());
        return myBooks;
    }

    public void removeBookById(String id) {
        // Owner can delete
        // check if owner has the right
        bookRepository.deleteById(id);
    }

    public void tradeBook(String id, String borrower) {
        //
    }
}
