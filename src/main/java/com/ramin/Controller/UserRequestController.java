package com.ramin.Controller;

import com.ramin.Dao.UserRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userRequests")
public class UserRequestController {

    @Autowired
    UserRequestDao userRequestDao;

    @RequestMapping(value = "/accTradeReq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getUserTradeRequests(@RequestBody Map<String,String> body) {
        this.userRequestDao.getUserRequests(body.get("email"));
    }

    //@RequestMapping(value = "/rejTradeReq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    //public void getUserTradeRequests(@RequestBody Map<String,String> body) {
    //    this.userRequestDao.getUserRequests(body.get("email"));
    //}
}
