package com.poli.inventory.services;



import com.poli.inventory.domain.MonitorModel;

import java.util.List;

public interface MonitorModelService {
    MonitorModel createMonitorModel(MonitorModel monitorModel) throws Exception;
    List<MonitorModel> consultMonitorModel(String idMonitor);

}
