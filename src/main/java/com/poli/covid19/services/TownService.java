package com.poli.covid19.services;



import com.poli.covid19.domain.Town;

import java.util.List;

public interface TownService {

    List<Town> getTownByDepartment(String id);

}
