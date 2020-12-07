package com.poli.inventory.controllers;


import com.poli.inventory.domain.ArticlesReported;
import com.poli.inventory.services.ArticlesReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ArticlesReportedController {

	@Autowired
		private ArticlesReportedService articlesReportedService;

	@GetMapping("/articlesReported")
	public List<ArticlesReported> getArticlesReported(String idReport) {

		return articlesReportedService.consultReport(idReport);
	}
	@PostMapping(path = "/articlesReported", consumes = "application/json", produces = "application/json")
	public ArticlesReported create(@RequestBody ArticlesReported articlesReported) throws Exception {
		return articlesReportedService.createArticlesReport(articlesReported);
	}


}