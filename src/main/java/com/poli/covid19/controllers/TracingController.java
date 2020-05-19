package com.poli.covid19.controllers;

import com.poli.covid19.domain.Tracing;
import com.poli.covid19.services.TracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class TracingController {

	@Autowired
	private TracingService tracingService;

	@GetMapping("/tracing")
	public List<Tracing> consultTracing() {
		return tracingService.consultTracing();
	}
	@PostMapping(path ="/tracing", consumes="application/json", produces = "application/json")
	public	Tracing createTracing(@RequestBody Tracing tracing){

		return tracingService.createTracing(tracing);
	}

}