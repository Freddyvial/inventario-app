package com.poli.covid19.controllers;

import com.poli.covid19.domain.AnswerPatient;
import com.poli.covid19.services.AnswerPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AnswerPatientController {


	@Autowired
	private AnswerPatientService answerPatientService;

	@PostMapping(path ="/answerPatient", consumes="application/json", produces = "application/json")
	public	AnswerPatient setAnswerPatient(@RequestBody AnswerPatient answerPatient){

		return answerPatientService.setAnswerPatient(answerPatient);
	}

}