package com.poli.covid19.controllers;

import com.poli.covid19.domain.User;
import com.poli.covid19.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class UserController {


	@Autowired
		private UserService userService;

	@GetMapping("/consultUser")
	public User consultUser(String userName,String password) {
		return userService.consultUser(userName, password);
	}


}