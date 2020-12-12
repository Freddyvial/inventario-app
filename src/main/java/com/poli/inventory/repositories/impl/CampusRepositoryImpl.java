package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Campus;
import com.poli.inventory.repositories.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CampusRepositoryImpl implements CampusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Campus> consultCampus() {
        String sql = "select * from u280625412_inventory.campus";
        List<Campus> campuses = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Campus.class));
        return campuses;
    }
    @Override
    public Campus createCampus(Campus campus) {

        if (String.valueOf(campus.getIdCampus()).equals("")||String.valueOf(campus.getIdCampus()).equals("0")) {

            return create(campus);

        } else {
            return update(campus);
        }
    }

    private Campus create(Campus campus) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO u280625412_inventory.campus (name, direction,logo) VALUES (?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, campus.getName());
                ps.setString(2, campus.getDirection());
                ps.setBinaryStream(3, getInputStreamImage(campus.getLogo()));
                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();
        campus.setIdCampus(key.intValue());
        return  campus;
    }
    private InputStream getInputStreamImage(byte[] image) {
        return new ByteArrayInputStream(image);
    }

    @Override
    public Campus checkCampus(String name) {
        String sql = "select * from u280625412_inventory.campus where name = ?";

        List<Campus> campuses = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper(Campus.class));
        return campuses.size() > 0 ? campuses.get(0) : null;
    }

    @Override
    public Campus update(Campus campus) {
        jdbcTemplate.update(
                "UPDATE campus SET name=?, direction=?, logo=? WHERE idCampus=?",
                campus.getName(),campus.getDirection(),campus.getLogo(),campus.getIdCampus());
        return campus;
    }


}
