package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.*;
import com.poli.inventory.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Report> consultReport(String idCampus) {
        String sql = "SELECT r.*,u.userName,u.idRole,u.idCampus,rm.name as nameRoom,s.name as nameState FROM u280625412_inventory.report as r\n" +
                "INNER JOIN u280625412_inventory.users as u on r.idUser=u.idUser\n" +
                "INNER JOIN u280625412_inventory.rooms as rm on r.idRoom=rm.idRoom\n" +
                "INNER JOIN u280625412_inventory.state as s on r.idState=s.idState\n" +
                "WHERE u.idCampus=?";
        List<Report> reports = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idCampus);
        for (Map row : rows) {
            Report report=new Report();
            report.setIdReport((int) row.get("idReport"));
            User user= new User();
            user.setIdUser((int)row.get("idUser"));
            user.setUserName((String)row.get("userName"));
            Campus campus =new Campus();
            campus.setIdCampus((int)row.get("idCampus"));
            user.setCampus(campus);
            report.setUser(user);
            Room room=new Room();
            room.setIdRoom((int)row.get("idRoom"));
            room.setName((String)row.get("nameRoom"));
            report.setRoom(room);
            State state =new State();
            state.setId((int)row.get("idState"));
            state.setName((String)row.get("nameState"));
            report.setState(state);
            report.setDate((Date) row.get("date"));
            reports.add(report);

        }
        return reports;
    }
    @Override
    public Report createReport(Report report) {

        if (String.valueOf(report.getIdReport()).equals("")||String.valueOf(report.getIdReport()).equals("0")) {

            return create(report);

        } else {
            return update(report);
        }
    }

    private Report create(Report report) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO u280625412_inventory.report (idUser,idRoom,idState) VALUES (?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, report.getUser().getIdUser());
                ps.setInt(2, report.getRoom().getIdRoom());
                ps.setInt(3, report.getState().getId());

                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();
        report.setIdReport(key.intValue());
        return  report;
    }

    @Override
    public Report checkReport(String date) {
        String sql = "select * from u280625412_inventory.report where date = ?";

        List<Report> reports = jdbcTemplate.query(sql, new Object[]{date}, new BeanPropertyRowMapper(Report.class));
        return reports.size() > 0 ? reports.get(0) : null;
    }

    @Override
    public Report update(Report report) {
        jdbcTemplate.update(
                "UPDATE u280625412_inventory.campus SET  idUser=?, idRoom=?, idState WHERE idReport=?",
                report.getUser().getIdUser(),report.getRoom().getIdRoom(),report.getState().getId(),report.getIdReport());
        return report;
    }
    @Override
    public List<Report> consultGeneralReport(String idCampus) {
        String sql = "SELECT r.idRoom,r.name as nameRoom,r.photo as photoRoom,u.userName,a.name as nameArticle,a.serial as serialArticle,a.photo as photoArticle,a.date as dateArticle\n" +
                "FROM u280625412_inventory.rooms as r\n" +
                "INNER JOIN u280625412_inventory.articles as a on r.idRoom=a.idRoom\n" +
                "INNER JOIN u280625412_inventory.users as u on r.idUser = u.idUser\n" +
                "WHERE u.idCampus=?";
        List<Report> reports = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idCampus);
        for (Map row : rows) {

        }
        return reports;
    }



}
