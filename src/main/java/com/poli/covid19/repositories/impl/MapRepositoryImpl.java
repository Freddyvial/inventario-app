package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.MapResultByLocation;
import com.poli.covid19.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MapRepositoryImpl implements MapRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MapResultByLocation> consultResultByLocation() {
        String sql = "SELECT p.latitude as lat,p.longitude as lng, s.value as label FROM covid19.patients as p\n" +
                "inner join covid19.state as s on p.idState= s.id";
        List<MapResultByLocation> mapResultByLocations = jdbcTemplate.query(sql, new BeanPropertyRowMapper(MapResultByLocation.class));
        return mapResultByLocations;
    }

}
