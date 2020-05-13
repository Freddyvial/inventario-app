package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.Town;
import com.poli.covid19.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TownRepositoryImpl implements TownRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Town> getTownByDepartment(String id) {
        String sql = "";

        sql = "select * from covid19.town where idDepartment= "+id;


        List<Town> towns = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Town.class));
        return towns;
    }


}
