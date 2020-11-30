package com.poli.inventory.repositories;





import com.poli.inventory.domain.Campus;
import com.poli.inventory.domain.Room;

import java.util.List;

public interface CampusRepository {

    List<Campus> consultCampus();
    Campus createCampus(Campus campus);
    Campus checkCampus(String name);
    Campus update(Campus campus);
}
