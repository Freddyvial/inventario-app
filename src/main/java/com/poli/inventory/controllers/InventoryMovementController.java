package com.poli.inventory.controllers;

import com.poli.inventory.domain.InventoryMovement;
import com.poli.inventory.services.InventoryMovementService;
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
public class InventoryMovementController {

	@Autowired
		private InventoryMovementService inventoryMovementService;

	@GetMapping("/inventoryMovement")
	public List<InventoryMovement> getInventoryMovement(String date,String idCampus) {

		return inventoryMovementService.consultInventoryMovement(date, idCampus);
	}
	@PostMapping(path = "/inventoryMovement", consumes = "application/json", produces = "application/json")
	public InventoryMovement createInventoryMovement(@RequestBody InventoryMovement inventoryMovement) throws Exception {
		return inventoryMovementService.createInventoryMovement(inventoryMovement);
	}
	@GetMapping("/generateReportInvMov")
	public void generateReportInvMov(HttpServletResponse response,String date,String idCampus) throws IOException, JRException, SQLException{
	response.setContentType("application/x-download");
	response.setHeader("Content-Disposition",String.format("attachment;filename=\"invMov.pdf\""));
		OutputStream out =response.getOutputStream();
 		inventoryMovementService.exportReport(date,idCampus,out);
	}



}