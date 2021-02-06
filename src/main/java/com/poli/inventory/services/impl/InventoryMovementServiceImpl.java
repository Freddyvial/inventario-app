package com.poli.inventory.services.impl;

import com.poli.inventory.domain.InventoryMovement;
import com.poli.inventory.repositories.InventoryMovementRepository;
import com.poli.inventory.services.InventoryMovementService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Service
public class InventoryMovementServiceImpl implements InventoryMovementService {

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;


    @Override
    public List<InventoryMovement> consultInventoryMovement(String date,String idCampus) {

        return inventoryMovementRepository.consultInventoryMovementReport(date,idCampus);
    }
    @Override
    public InventoryMovement createInventoryMovement(InventoryMovement inventoryMovement) throws Exception {
        if (inventoryMovement.getIdInvMov()== 0) {
            return inventoryMovementRepository.createInventoryMovement(inventoryMovement);
        } else {
              return null;
        }
    }
    @Override
    public void exportReport(String date,String idCampus, OutputStream outputStream) throws JRException, IOException {
        JasperPrint jasperPrint = inventoryMovementRepository.exportReport(date,idCampus);
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }





}
