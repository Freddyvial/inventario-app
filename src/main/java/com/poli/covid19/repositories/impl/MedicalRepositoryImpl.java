package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.Medical;
import com.poli.covid19.repositories.MedicalRepository;

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
public class MedicalRepositoryImpl implements MedicalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Medical> getMedical(String id) {
        String sql ="";
        if(id==null || id.equals("")){
            sql = "select * from covid19.medical";
        }else{
            sql = "select * from covid19.medical where id ="+ id;
        }

        List<Medical> medicals = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Medical.class));
        return medicals;
    }

    @Override
    public Medical createMedical(Medical medical) {
    if(medical.getId()==null||medical.getId().equals("")){
        return create(medical);

    }else {
        return update(medical);

    }

        }

    private Medical update(Medical medical) {
        jdbcTemplate.update(
                "UPDATE covid19.medical SET fullName = ?,idSpecialty = ?,email = ? WHERE id = ?",
                medical.getFullName(),medical.getSpecialty().getId(),medical.getEmail(), medical.getId());
    return medical;
    }


    private Medical create(Medical medical) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO covid19.medical (fullName,idSpecialty,email) values(?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, medical.getFullName());
                ps.setString(2, medical.getSpecialty().getId());
                ps.setString(3, medical.getEmail());


                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        medical.setId(key.toString());

        return medical  ;
    }
}
