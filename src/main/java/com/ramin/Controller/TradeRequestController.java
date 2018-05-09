package com.ramin.Controller;

import com.ramin.Dao.TradeRequestDao;
import com.ramin.Entity.TradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tradeRequests")
public class TradeRequestController {

    @Autowired
    TradeRequestDao tradeRequestDao;

    @ResponseBody
    @RequestMapping(value="/addTradeReq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTradeRequest(@RequestBody TradeRequest tradeRequest) {
        this.tradeRequestDao.insertTradeRequest(tradeRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/delTradeReq", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeTradeRequest(@RequestBody Map<String,String>body) {
        this.tradeRequestDao.removeTradeRequestById(body.get("id"));
    }

    @RequestMapping(value = "/accTradeReq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void acceptTradeRequest(@RequestBody Map<String,String> body) {
        this.tradeRequestDao.acceptTradeRequestById(body.get("id"));
    }

    @RequestMapping(value = "/rejTradeReq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void rejectTradeRequest(@RequestBody Map<String,String> body) {
        this.tradeRequestDao.rejectTradeRequestById(body.get("id"));
    }

}
