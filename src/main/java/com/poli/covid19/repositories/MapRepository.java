package com.poli.covid19.repositories;


import com.poli.covid19.domain.MapResultByLocation;

import java.util.List;

public interface MapRepository {
    List<MapResultByLocation> consultResultByLocation();
}


