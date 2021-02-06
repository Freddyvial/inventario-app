package com.poli.inventory.repositories;
import com.poli.inventory.domain.InventoryMovement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import java.io.FileNotFoundException;
import java.util.List;

public interface InventoryMovementRepository {

    List<InventoryMovement> consultReport(String idCampus);
    InventoryMovement createInventoryMovement(InventoryMovement inventoryMovement);
    InventoryMovement checkInventoryMovement(String date);
    List<InventoryMovement> consultInventoryMovementReport(String date,String idCampus);
    JasperPrint exportReport(String idCampus,String date) throws FileNotFoundException, JRException;
}
