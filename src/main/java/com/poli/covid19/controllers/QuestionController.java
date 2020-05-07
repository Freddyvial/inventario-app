package com.poli.covid19.controllers;

import com.poli.covid19.domain.Question;
import com.poli.covid19.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class QuestionController {


	@Autowired
	private QuestionService questionService;

	@GetMapping("/questions")
	public List<Question> getQuestions() {

		return questionService.getQuestions();
	}


}