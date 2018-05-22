package com.ramin.Controller;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.ramin.Dao.BookDao;
import com.ramin.Dao.TokenDao;
import com.ramin.Entity.Book;
//import jdk.nashorn.internal.parser.JSONParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value="/addBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <JsonValue> insertBook(@RequestBody Map<String,String> body) throws IOException {

        String query = body.get("bookName");

        JsonArray arr =  get("https://www.googleapis.com/books/v1/volumes?q="+query);

        System.out.println(arr.get(0));

        JsonValue value = arr.get(0);

        System.out.println("value " + value);

        JsonArray res = (JsonArray) value;
        JsonValue res2 = (JsonValue) (res);
        //System.out.println(res2);

        return new ResponseEntity<JsonValue>(res2, HttpStatus.OK);
    }

    private JsonArray get(String query) throws IOException {
        final String uri = "https://www.googleapis.com/books/v1/volumes?q=" + query;

        RestTemplate restTemplate = new RestTemplate();
        String result = String.valueOf(restTemplate.getForObject(uri, String.class));

        JsonObject value = (JsonObject) Json.parse(result);

        return (JsonArray) value.get("items");
    }

}
