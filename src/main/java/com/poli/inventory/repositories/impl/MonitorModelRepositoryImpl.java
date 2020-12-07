package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.MonitorModel;
import com.poli.inventory.domain.User;
import com.poli.inventory.repositories.MonitorModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MonitorModelRepositoryImpl implements MonitorModelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MonitorModel> consulModelByMonitor(String idMonitor) {
        String sql = "SELECT * FROM roominventory.monitormodel WHERE idMonitor=?";
        List<MonitorModel> monitorModels = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idMonitor);
        for (Map row : rows) {
            MonitorModel monitorModel=new MonitorModel();
            User monitor= new User();
            User model= new User();
            monitor.setIdUser((int) row.get("idMonitor"));
            model.setIdUser((int) row.get("idModel"));
            monitorModel.setUserMonitor(monitor);
            monitorModel.setUserModel(model);
            monitorModels.add(monitorModel);        }
        return monitorModels;
    }
    @Override
    public MonitorModel create(MonitorModel monitorModel) {
        String sql = "INSERT INTO roominventory.monitormodel (idMonitor,idModel) VALUES (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, monitorModel.getUserMonitor().getIdUser());
                ps.setInt(2, monitorModel.getUserModel().getIdUser());
                return ps;
            }
        });
        return  monitorModel;
    }
    @Override
    public MonitorModel checkMonitorModel(String idMonitor,String idModel) {
        String sql = "select * from roominventory.report where idMonitor = ? AND idModel=?";

        List<MonitorModel> monitorModels = jdbcTemplate.query(sql, new Object[]{idMonitor,idModel}, new BeanPropertyRowMapper(MonitorModel.class));
        return monitorModels.size() > 0 ? monitorModels.get(0) : null;
    }




}
