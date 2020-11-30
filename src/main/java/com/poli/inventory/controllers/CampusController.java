package com.poli.inventory.controllers;


import com.poli.inventory.domain.Campus;
import com.poli.inventory.domain.Room;
import com.poli.inventory.services.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	@PostMapping(path = "/campus", consumes = "application/json", produces = "application/json")
	public Campus sendCampus(@RequestBody Campus campus) throws Exception {

		return campusService.createCampus(campus);

	}


}