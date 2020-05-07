package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.Patient;
import com.poli.covid19.repositories.PatientRepository;
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
public class PatientsRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Patient> getPatients(String id) {
        String sql ="";
        if(id==null || id.equals("")){
            sql = "select * from covid19.patients";
        }else{
            sql = "select * from covid19.patients where id ="+ id;
        }

        List<Patient> patients = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Patient.class));
        return patients;
    }

    @Override
    public Patient createPatients(Patient patient) {
    if(patient.getId()==null||patient.getId().equals("")){
        return create(patient);

    }else {
        return update(patient);

    }

        }

    private Patient update(Patient patient) {
        jdbcTemplate.update(
                "UPDATE covid19.patients SET documentNumber = ?,fullName = ?,direction = ?,phone = ?,email = ?,idDocumentType = ?,idTown=?,idState=? WHERE id = ?",
                patient.getDocumentNumber(),patient.getFullName(),patient.getDirection(),patient.getPhone(),patient.getEmail(),patient.getDocumentType().getId(),patient.getTown().getId(),patient.getState().getId(), patient.getId());
    return patient;
    }


    private Patient create(Patient patient) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO covid19.patients (documentNumber,fullName,direction,phone,email,idDocumentType,idTown,idState) values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, patient.getDocumentNumber());
                ps.setString(2, patient.getFullName());
                ps.setString(3, patient.getDirection());
                ps.setString(4, patient.getPhone());
                ps.setString(5, patient.getEmail());
                ps.setString(6, patient.getDocumentType().getId());
                ps.setString(7, patient.getTown().getId());
                ps.setString(8, patient.getState().getId());

                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        patient.setId(key.toString());

        return patient  ;
    }
}
