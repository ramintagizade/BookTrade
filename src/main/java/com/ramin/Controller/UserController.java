package com.ramin.Controller;

import com.ramin.Dao.TokenDao;
import com.ramin.Dao.UserDao;
import com.ramin.Entity.User;
import com.ramin.Entity.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.security.Key;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3000)
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    TokenDao tokenDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> login( @RequestBody Map<String ,String > body ) {

        String email = body.get("email");
        String password = body.get("password");

        String passwordDB = getPasswordDB(email);

        String key = this.tokenDao.getToken().get().getToken();

        String compactJws = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        body.remove("password");


        if(BCrypt.checkpw(password, passwordDB)) {
            body.put("token",compactJws);
            return body;
        }
        else {
            return body;
        }
    }

    public String getPasswordDB(String id) {
        return this.userDao.findPasswordById(id);
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String ,String> register(@RequestBody Map<String,String> body) {

        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");

        body.remove("username");
        body.remove("password");

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User(username,email,hashed);
        System.out.println("register " + username + " " + email + " " + password );


        //Key key = MacProvider.generateKey();
        String key = this.tokenDao.getToken().get().getToken();

        String compactJws = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        try {
            // success
            Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);

            System.out.println("jwt success");

        } catch (SignatureException e) {
            // error
            System.out.println("jwt error " + e);
        }

        boolean ok = this.userDao.registerUser(user);

        if(ok) {
            body.put("token",compactJws);
            return body;
        }
        else {
            return body;
        }
    }

    @RequestMapping(value = "/settings" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String ,String> settings(@RequestBody Map<String,String> body) {

        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        String newPassword = body.get("newPassword");

        String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));


        User user = new User(username,email,hashed);

        body.remove("username");
        body.remove("password");
        body.remove("newPassword");

        System.out.println("settings " + username + " " + email + " " + password + " " + newPassword );

        String key = this.tokenDao.getToken().get().getToken();

        String compactJws = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        String passwordDB = getPasswordDB(email);

        if(BCrypt.checkpw(password, passwordDB)) {
            this.userDao.updateUser(user);
            body.put("token",compactJws);
            return  body;
        }
        else {
            return body;
        }
    }
}
