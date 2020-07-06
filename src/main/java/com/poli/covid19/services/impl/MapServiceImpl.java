package com.poli.covid19.services.impl;


import com.poli.covid19.domain.MapResultByLocation;
import com.poli.covid19.repositories.MapRepository;
import com.poli.covid19.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private MapRepository mapRepository;

    @Override
    public List<MapResultByLocation> consultResultByLocation() {

        return mapRepository.consultResultByLocation();
    }


}
