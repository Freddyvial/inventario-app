package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Campus;
import com.poli.inventory.domain.InventoryMovement;
import com.poli.inventory.domain.TypeInvMov;
import com.poli.inventory.domain.User;
import com.poli.inventory.repositories.InventoryMovementRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class InventoryMovementsRepositoryImpl implements InventoryMovementRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<InventoryMovement> consultReport(String idCampus) {
        String sql = "SELECT i.*,t.name AS nameTypeMov,u.userName,u.idCampus,c.name AS nameCampus\n" +
                "FROM `inventorymovements` AS i \n" +
                "INNER JOIN typemovements AS t on i.idTypeMov=t.idTypeMov\n" +
                "INNER JOIN users AS u ON i.idUser= u.idUser\n" +
                "INNER JOIN campus AS c ON u.idCampus=c.idCampus\n" +
                "WHERE u.idCampus=?";
        List<InventoryMovement> inventoryMovements = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idCampus);
        for (Map row : rows) {
            InventoryMovement inventoryMovement =new InventoryMovement();
            inventoryMovement.setIdInvMov((int) row.get("idInvMov"));
            TypeInvMov typeInvMov=new TypeInvMov();
            typeInvMov.setIdTypeInvMov((int) row.get("idTypeMov"));
            typeInvMov.setName((String) row.get("nameTypeMov"));
            inventoryMovement.setTypeInvMov(typeInvMov);
            User user= new User();
            user.setIdUser((int) row.get("idUser"));
            user.setUserName((String) row.get("userName"));
            Campus campus = new Campus();
            campus.setIdCampus((int) row.get("idCampus"));
            campus.setName((String) row.get("nameCampus"));
            user.setCampus(campus);
            inventoryMovement.setUser(user);
            inventoryMovement.setDetail((String) row.get("detail"));
            inventoryMovements.add(inventoryMovement);

        }
        return inventoryMovements;
    }
    @Override
    public InventoryMovement createInventoryMovement(InventoryMovement inventoryMovement) {

        if (String.valueOf(inventoryMovement.getIdInvMov()).equals("")||String.valueOf(inventoryMovement.getIdInvMov()).equals("0")) {

            return create(inventoryMovement);

        } else return null;
    }

    private InventoryMovement create(InventoryMovement inventoryMovement) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO u280625412_inventory.inventorymovements (idTypeMov,idUser,detail) VALUES (?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, inventoryMovement.getTypeInvMov().getIdTypeInvMov());
                ps.setInt(2, inventoryMovement.getUser().getIdUser());
                ps.setInt(3, inventoryMovement.getUser().getIdUser());

                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();
        inventoryMovement.setIdInvMov(key.intValue());
        return  inventoryMovement;
    }

    @Override
    public InventoryMovement checkInventoryMovement(String date) {
        String sql = "select * from u280625412_inventory.inventorymovements where date = ?";

        List<InventoryMovement> inventoryMovements = jdbcTemplate.query(sql, new Object[]{date}, new BeanPropertyRowMapper(InventoryMovement.class));
        return inventoryMovements.size() > 0 ? inventoryMovements.get(0) : null;
    }


    @Override
    public List<InventoryMovement> consultInventoryMovementReport(String date,String idCampus) {
        String sql = "SELECT i.*,t.name AS nameTypeMov,u.userName,u.idCampus,c.name AS nameCampus\n" +
                "FROM `inventorymovements` AS i \n" +
                "INNER JOIN typemovements AS t on i.idTypeMov=t.idTypeMov\n" +
                "INNER JOIN users AS u ON i.idUser= u.idUser\n" +
                "INNER JOIN campus AS c ON u.idCampus=c.idCampus\n" +
                "WHERE i.date=? AND i.idCampus=?";
        List<InventoryMovement> inventoryMovements = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,date,idCampus);
        for (Map row : rows) {
            InventoryMovement inventoryMovement =new InventoryMovement();
            inventoryMovement.setIdInvMov((int) row.get("idInvMov"));
            TypeInvMov typeInvMov=new TypeInvMov();
            typeInvMov.setIdTypeInvMov((int) row.get("idTypeMov"));
            typeInvMov.setName((String) row.get("nameTypeMov"));
            inventoryMovement.setTypeInvMov(typeInvMov);
            User user= new User();
            user.setIdUser((int) row.get("idUser"));
            user.setUserName((String) row.get("userName"));
            Campus campus = new Campus();
            campus.setIdCampus((int) row.get("idCampus"));
            campus.setName((String) row.get("nameCampus"));
            user.setCampus(campus);
            inventoryMovement.setUser(user);
            inventoryMovement.setDetail((String) row.get("detail"));
            inventoryMovements.add(inventoryMovement);

        }
        return inventoryMovements;
    }

    @Override
    public JasperPrint exportReport(String idCampus,String date) throws FileNotFoundException, JRException {

        List<InventoryMovement> generalReports = consultInventoryMovementReport(date,idCampus);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:_______.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(generalReports);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return jasperPrint;
    }



}
