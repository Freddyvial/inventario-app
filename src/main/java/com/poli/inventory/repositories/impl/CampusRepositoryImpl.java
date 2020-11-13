package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Campus;
import com.poli.inventory.repositories.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CampusRepositoryImpl implements CampusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Campus> consultCampus() {
        String sql = "select * from roominventory.campus";
        List<Campus> campuses = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Campus.class));
        return campuses;
    }


}
