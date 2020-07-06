package com.poli.covid19.services;


import com.poli.covid19.domain.MapResultByLocation;

import java.util.List;

public interface MapService {
    List<MapResultByLocation> consultResultByLocation();

}
