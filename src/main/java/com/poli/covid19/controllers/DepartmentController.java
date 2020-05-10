package com.poli.covid19.controllers;


import com.poli.covid19.domain.Department;
import com.poli.covid19.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DepartmentController {


	@Autowired
		private DepartmentService departmentService;

	@GetMapping("/department")
	public List<Department> getDepartment() {

		return departmentService.getDepartment();
	}


}