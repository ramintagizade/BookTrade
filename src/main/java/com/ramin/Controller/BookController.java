package com.ramin.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ramin.Dao.BookDao;
import com.ramin.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Book> getAllBooks() {
        return this.bookDao.getAllBooks();
    }

    @RequestMapping(method = RequestMethod.GET , value = "/{id}")
    public Optional<Book> getBookById(@PathVariable("id") String id) {
        return this.bookDao.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "myBooks/{owner}")
    public Collection<Book> getMyBooks(@PathVariable("owner") String owner) {
        return this.bookDao.getMyBooks(owner);
    }

    @RequestMapping(value = "/delete/id" ,method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeBookById(@RequestBody Map<String, String> body){

        if(getHeaderToken().equals(("Bearer secret"))) {
            this.bookDao.removeBookById(body.get("id"));
        }
    }
    //get request header token
    private String getHeaderToken() {
        return request.getHeader("authorization");
    }

    @RequestMapping(value="/trade/id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void tradeBook(@RequestBody Map<String,String>body) {
        this.bookDao.tradeBook(body.get("id"),body.get("borrower"));
    }
}
