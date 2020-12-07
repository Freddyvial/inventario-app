package com.poli.inventory.repositories;





import com.poli.inventory.domain.MonitorModel;


import java.util.List;

public interface MonitorModelRepository {

    List<MonitorModel> consulModelByMonitor(String idMonitor);
    MonitorModel create(MonitorModel monitorModel);
    MonitorModel checkMonitorModel(String idMonitor,String idModel);
}
