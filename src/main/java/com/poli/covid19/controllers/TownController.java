package com.poli.covid19.controllers;

import com.poli.covid19.domain.Town;
import com.poli.covid19.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class TownController {


	@Autowired
		private TownService townService;

	@GetMapping("/townByDepartment")
	public List<Town> getTownByDepartment(String id) {

		return townService.getTownByDepartment(id);
	}


}