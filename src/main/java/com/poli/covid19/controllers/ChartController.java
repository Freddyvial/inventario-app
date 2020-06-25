package com.poli.covid19.controllers;


import com.poli.covid19.domain.ChartResultByBirthDate;
import com.poli.covid19.domain.ChartResultByDepartment;
import com.poli.covid19.domain.ChartResultByDepartmentByState;
import com.poli.covid19.domain.ChartResultByState;
import com.poli.covid19.services.ChartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/resultByDepartment")
    public List<ChartResultByDepartment> consultChartResult() {

        return chartService.consultResultByDepartment();
    }
    @GetMapping("/resultByState")
    public List<ChartResultByState> consultChartByState() {

        return chartService.consultResultByStates();
    }
    @GetMapping("/resultByDepartmentByState")
    public List<ChartResultByDepartmentByState> consultChartByDepartmentByState() {

        return chartService.consultResultByDepartmentByState();
    }
    @GetMapping("/resultByBirthDate")
    public List<ChartResultByBirthDate> consultChartByBirthDate() {

        return chartService.consultResultByBirthDate();
    }



}