package com.ramin.Controller;

import com.ramin.Dao.TradeRequestDao;
import com.ramin.Dao.UserRequestDao;
import com.ramin.Entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/userRequests")
public class UserRequestController {

    @Autowired
    UserRequestDao userRequestDao;

    @Autowired
    TradeRequestDao tradeRequestDao;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<UserRequest> getUserTradeRequests(@RequestBody Map<String,String> body) {
        return this.userRequestDao.getUserRequests(body.get("id"));
    }


}
