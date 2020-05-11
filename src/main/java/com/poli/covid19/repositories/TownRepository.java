package com.poli.covid19.repositories;






import com.poli.covid19.domain.Town;

import java.util.List;

public interface TownRepository {

    List<Town> getTownByDepartment(String id);

}
