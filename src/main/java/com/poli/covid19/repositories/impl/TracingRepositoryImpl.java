package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.Tracing;
import com.poli.covid19.repositories.TracingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class TracingRepositoryImpl implements TracingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tracing> getTracing(String id) {
        String sql ="";
        if(id==null || id.equals("")){
            sql = "select * from covid19.tracing";
        }else{
            sql = "select * from covid19.tracing where id ="+ id;
        }

        List<Tracing> tracings = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Tracing.class));
        return tracings;
    }

    @Override
    public Tracing createTracing(Tracing tracing) {
    if(tracing.getId()==null|| tracing.getId().equals("")){
        return create(tracing);

    }else {
            return update(tracing);

    }

        }

    private Tracing update(Tracing tracing) {
        jdbcTemplate.update(
                "UPDATE covid19.tracing SET idPatient = ?,idMedical = ?,observations = ?,idState = ? WHERE id = ?",
                tracing.getPatient(),tracing.getMedical(),tracing.getObservation(),tracing.getState());
    return tracing;
    }


    private Tracing create(Tracing tracing) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO covid19.tracing (idPatient,idMedical,observations,idState) values(?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tracing.getPatient().getId());
                ps.setString(2, tracing.getMedical().getId());
                ps.setString(3, tracing.getObservation());
                ps.setString(4, tracing.getState().getId());


                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        tracing.setId(key.toString());

        return tracing  ;
    }
}
