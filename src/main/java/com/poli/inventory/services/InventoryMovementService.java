package com.poli.inventory.services;


import com.poli.inventory.domain.InventoryMovement;
import com.poli.inventory.domain.Report;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public interface InventoryMovementService {
    List<InventoryMovement> consultInventoryMovement(String date, String idCampus);
    InventoryMovement createInventoryMovement(InventoryMovement inventoryMovement) throws Exception;
    public void exportReport(String date,String idCampus, OutputStream outputStream) throws JRException, IOException, SQLException;
}
