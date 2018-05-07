package com.ramin.Controller;

import com.ramin.Dao.TradeRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tradeRequests")
public class TradeRequestController {

    @Autowired
    TradeRequestDao tradeRequestDao;

    @RequestMapping(value="/id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void tradeRequests(@RequestBody Map<String,String> body) {

    }
}
