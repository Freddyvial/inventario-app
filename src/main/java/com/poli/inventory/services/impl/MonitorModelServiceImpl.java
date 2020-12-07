package com.poli.inventory.services.impl;

import com.poli.inventory.domain.MonitorModel;
import com.poli.inventory.repositories.MonitorModelRepository;
import com.poli.inventory.services.MonitorModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorModelServiceImpl implements MonitorModelService {

    @Autowired
    private MonitorModelRepository monitorModelRepository;

    @Override
    public List<MonitorModel> consultMonitorModel(String idMonitor) {

        return monitorModelRepository.consulModelByMonitor(idMonitor);
    }

    @Override
    public MonitorModel createMonitorModel(MonitorModel monitorModel) throws Exception {
        MonitorModel monitorModelExits = monitorModelRepository.checkMonitorModel(String.valueOf(monitorModel.getUserMonitor().getIdUser()),String.valueOf(monitorModel.getUserModel().getIdUser()));

        if (monitorModelExits == null) {
            return monitorModelRepository.create(monitorModel);
        } else {
            return null;


        }
    }

}
