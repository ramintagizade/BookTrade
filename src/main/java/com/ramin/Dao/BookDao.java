package com.ramin.Dao;

import com.ramin.Entity.Book;
import com.ramin.Entity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookDao implements CommandLineRunner {



    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String ... args) {
        //bookRepository.insert(new Book("Java EE","Jessy","stackoverflow.com"));
        System.out.println(bookRepository.findAll().toString());
    }

    public void insertBook(Book book) {
        this.bookRepository.insert(book);
    }

    public Optional<Book> getBookById(String id) {
        return this.bookRepository.findById(id);
    }

    public Collection<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Collection<Book> getMyBooks(String owner) {
        Collection<Book> myBooks = this.getAllBooks().stream()
                .filter(p -> p.getOwner().equals(owner) ).collect(Collectors.toList());
        return myBooks;
    }

    public void removeBookById(String id) {
        // Owner can delete
        // check if owner has the right
        this.bookRepository.deleteById(id);
    }

    public void tradeBook(String id, String borrower) {
        Optional<Book> myBook =  this.getBookById(id);
        myBook.get().setOwner(borrower);
        this.updateBook(myBook.get());
    }

    public void updateBook(Book book) {
        this.bookRepository.save(book);
    }


}
