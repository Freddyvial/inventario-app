package com.poli.covid19.controllers;



import com.poli.covid19.domain.DetailTracing;
import com.poli.covid19.domain.*;

import com.poli.covid19.services.TracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class TracingController {
	@Autowired
	private TracingService tracingService;

	@GetMapping("/detailTracing")
	public List<DetailTracing> consultDetailTracing(@RequestParam(required = false) String id) {

		return tracingService.consultDetailTracing(id);
	}


	@GetMapping("/tracing")
	public List<Tracing> consultTracing(String id) {

		return tracingService.consultTracing(id);
	}


	@PostMapping(path ="/tracing", consumes="application/json", produces = "application/json")
	public	Tracing createTracing(@RequestBody Tracing tracing){

		return tracingService.createTracing(tracing);
	}

	@PostMapping(path ="/createDetailTracing", consumes="application/json", produces = "application/json")
	public	DetailTracing createDetailTracing(@RequestBody DetailTracing detailTracing){

		return tracingService.createDetailTracing(detailTracing);
	}

}