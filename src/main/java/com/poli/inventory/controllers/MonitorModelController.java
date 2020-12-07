package com.poli.inventory.controllers;
import com.poli.inventory.domain.MonitorModel;
import com.poli.inventory.services.MonitorModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class MonitorModelController {

	@Autowired
		private MonitorModelService monitorModelService;

	@GetMapping("/monitorModel")
	public List<MonitorModel> getMonitorModel(String idMonitor) {
		return monitorModelService.consultMonitorModel(idMonitor);
	}
	@PostMapping(path = "/monitorModel", consumes = "application/json", produces = "application/json")
	public MonitorModel create(@RequestBody MonitorModel monitorModel) throws Exception {
		return monitorModelService.createMonitorModel(monitorModel);
	}


}