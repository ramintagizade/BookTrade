package com.ramin.Controller;

import ch.qos.logback.classic.net.SyslogAppender;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramin.Dao.BookDao;
import com.ramin.Dao.TokenDao;
import com.ramin.Entity.Book;
//import jdk.nashorn.internal.parser.JSONParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@RestController
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3000)
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenDao tokenDao;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Book> getAllBooks() {
        return this.bookDao.getAllBooks();
    }

    @RequestMapping(method = RequestMethod.GET , value = "/id/{id}")
    public Optional<Book> getBookById(@PathVariable("id") String id) {
        return this.bookDao.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "myBooks")
    public Collection<Book> getMyBooks(@RequestBody Map<String ,String > body) {

        String key = this.tokenDao.getToken().get().getToken();
        try {
                // success
                Jwts.parser().setSigningKey(key).parseClaimsJws(getHeaderToken());
                System.out.println("jwt success");
                return this.bookDao.getMyBooks(body.get("email"));
        } catch (SignatureException e) {
                // error
            System.out.println("jwt err");
            return null;
        }

    }

    @RequestMapping(value = "/delete/id" ,method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeBookById(@RequestBody Map<String, String> body){

        String key = this.tokenDao.getToken().get().getToken();
        try {
            // success
            Jwts.parser().setSigningKey(key).parseClaimsJws(getHeaderToken());
            System.out.println("jwt success");
            this.bookDao.removeBookById(body.get("id"));
        } catch (SignatureException e) {
            // error
            System.out.println("jwt err");
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

    @RequestMapping(value="/addBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String > insertBook(@RequestBody Map<String,String> body) throws IOException {
        System.out.print(body);
        String name = body.get("name");
        JsonArray arr =  get("https://www.googleapis.com/books/v1/volumes?q="+name);
        if(arr==null) return null;

        JsonObject book = arr.get(0).asObject();

        String id = book.get("id").asString();
        String url = book.get("volumeInfo").asObject().get("imageLinks").asObject().get("thumbnail").asString();

        Map<String ,String > map = new HashMap<String ,String>();
        Book myBook = new Book(body.get("bookName"),body.get("owner"), new String(url),new String(id));
        this.bookDao.insertBook(myBook);

        map.put("name",name);
        map.put("owner",body.get("owner"));
        map.put("url",url);
        map.put("id",id);

        return map;
    }

    private JsonArray get(String query) throws IOException {
        final String uri = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        RestTemplate restTemplate = new RestTemplate();
        String result = String.valueOf(restTemplate.getForObject(uri, String.class));
        JsonObject value = (JsonObject) Json.parse(result);
        if(value==null) return null;
        return  (JsonArray) value.get("items");
    }

}
