package com.ramin.Controller;

import com.ramin.Dao.UserRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRequests")
public class UserRequestController {

    @Autowired
    UserRequestDao userRequestDao;


}
