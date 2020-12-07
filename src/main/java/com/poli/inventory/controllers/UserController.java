package com.poli.inventory.controllers;

import com.poli.inventory.domain.User;
import com.poli.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/consultUserByCampus")
    public List<User> consultUserByCampus(String idCampus) {
        return userService.consultUserByCampus(idCampus);
    }

    @PostMapping(path = "/createUser", consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user,String idRole) throws Exception {
        return userService.setUser(user,idRole);
    }
}