package com.poli.inventory.controllers;


import com.poli.inventory.domain.Campus;
import com.poli.inventory.services.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class CampusController {

	@Autowired
		private CampusService campusService;

	@GetMapping("/campus")
	public List<Campus> getCampus() {

		return campusService.consultCampus();
	}


}