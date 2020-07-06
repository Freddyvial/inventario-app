package com.poli.covid19.controllers;


import com.poli.covid19.domain.MapResultByLocation;
import com.poli.covid19.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class MapController {

    @Autowired
    private MapRepository mapRepository;

    @GetMapping("/resultByLocation")
    public List<MapResultByLocation> consultResultByLocation() {

        return mapRepository.consultResultByLocation();
    }



}