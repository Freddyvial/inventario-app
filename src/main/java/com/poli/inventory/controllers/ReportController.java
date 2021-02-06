package com.poli.inventory.controllers;

import com.poli.inventory.domain.Report;
import com.poli.inventory.services.ReportService;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
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
	@GetMapping("/jasper")
	public void generateReport(HttpServletResponse response,String idCampus) throws IOException, JRException, SQLException{
	response.setContentType("application/x-download");
	response.setHeader("Content-Disposition",String.format("attachment;filename=\"person.pdf\""));
		OutputStream out =response.getOutputStream();
 		reportService.exportReport(idCampus,out);
	}



}