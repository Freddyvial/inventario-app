package com.poli.covid19.controllers;

import com.poli.covid19.domain.Medical;
import com.poli.covid19.services.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class MedicalController {

	@Autowired
	private MedicalService medicalService;

	@GetMapping("/medical")
	public List<Medical> getMedical(@RequestParam(required =false) String id) {
		System.out.println("ID: "+ id);
		return medicalService.getMedical(id);
	}
	@PostMapping(path ="/medical", consumes="application/json", produces = "application/json")
	public	Medical createMedical(@RequestBody Medical medical){

		return medicalService.createMedical(medical);
	}

}