package com.poli.inventory.controllers;

import com.poli.inventory.domain.User;
import com.poli.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/consultUser")
    public User consultUser(String userName, String password) {
        return userService.consultUser(userName, password);
    }

    @PostMapping(path = "/upDatePassword", consumes = "application/json", produces = "application/json")
    public User upDatePassword(@RequestBody User user) {

        return userService.upDatePassword(user);


    }
}