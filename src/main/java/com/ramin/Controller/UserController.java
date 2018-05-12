package com.ramin.Controller;

import com.ramin.Dao.UserDao;
import com.ramin.Entity.User;
import com.ramin.Entity.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody Map<String ,String > body) {

        String email = body.get("email");
        String password = body.get("password");

        String passwordDB = getPasswordDB(email);

        if(BCrypt.checkpw(password, passwordDB)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public String getPasswordDB(String id) {
        return this.userDao.findPasswordById(id);
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody Map<String,String> body) {

        String email = body.get("email");
        String password = body.get("password");

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User(email,hashed);

        this.userDao.registerUser(user);
    }
}
