package com.poli.inventory.controllers;

import com.poli.inventory.domain.Article;
import com.poli.inventory.domain.Report;
import com.poli.inventory.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ReportController {

	@Autowired
		private ReportService reportService;

	@GetMapping("/report")
	public List<Report> getReport(String idCampus) {

		return reportService.consultReport(idCampus);
	}
	@PostMapping(path = "/reports", consumes = "application/json", produces = "application/json")
	public Report createReport(@RequestBody Report report) throws Exception {
		return reportService.createReport(report);
	}


}