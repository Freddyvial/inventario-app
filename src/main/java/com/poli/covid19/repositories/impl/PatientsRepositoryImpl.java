package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.*;
import com.poli.covid19.repositories.PatientRepository;
import com.poli.covid19.repositories.UserRepository;
import com.poli.covid19.services.UserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PatientsRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Patient> getPatients() {
        String sql = "select p.*,dt.value as nameDocumentType,t.name as nameTown,t.idDepartment,d.name as nameDepartment,\n" +
                "s.value as nameState,u.userName,u.idRole,r.name as nameRole from covid19.patients as p\n" +
                "inner join covid19.documenttype as dt on p.idDocumentType=dt.id\n" +
                "inner join covid19.town as t on p.idTown= t.id\n" +
                "inner join covid19.departments as d on t.idDepartment=d.id\n" +
                "inner join covid19.state as s on p.idState = s.id\n" +
                "inner join covid19.users as u on p.idUser=u.id\n" +
                "inner join covid19.role as r on u.idRole=r.id";
        List<Patient> patients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Patient newPatient=new Patient();
            newPatient.setId(String.format(row.get("id").toString()));
            newPatient.setDocumentNumber((String) row.get("documentNumber"));
            newPatient.setFullName((String) row.get("fullName"));
            newPatient.setDirection((String) row.get("direction"));
            newPatient.setPhone((String) row.get("phone"));
            newPatient.setEmail((String) row.get("email"));
            DocumentType documentType = new DocumentType();
            documentType.setId(String.format(row.get("idDocumentType").toString()));
            documentType.setValue((String) row.get("nameDocumentType"));
            newPatient.setDocumentType(documentType);
            Town town = new Town();
            town.setId(String.format(row.get("idTown").toString()));
            town.setIdDepartment(String.format(row.get("idDepartment").toString()));
            town.setName((String) row.get("nameTown"));
            newPatient.setTown(town);
            Department department = new Department();
            department.setId(String.format(row.get("idDepartment").toString()));
            department.setName((String) row.get("nameDepartment"));
            newPatient.setDepartment(department);
            State statePatient = new State();
            statePatient.setId(String.format(row.get("idState").toString()));
            statePatient.setValue((String) row.get("nameState"));
            newPatient.setState(statePatient);
            newPatient.setChangeDate(String.format(row.get("changeDate").toString()));
            newPatient.setBirthDate((String) row.get("birthDate"));
            User userPatient = new User();
            userPatient.setId(String.format(row.get("idUser").toString()));
            userPatient.setUserName((String) row.get("userName"));
            Role roleUser=new Role();
            roleUser.setId(String.format(row.get("idRole").toString()));
            roleUser.setName((String) row.get("nameRole"));
            userPatient.setRole(roleUser);
            newPatient.setUser(userPatient);
            newPatient.setLatitude((String)row.get("latitude"));
            newPatient.setLatitude((String)row.get("longitude"));
            newPatient.setResult((String) row.get("result"));

            patients.add(newPatient);
        }

        return patients;
    }

    @Override
    public Patient createPatients(Patient patient) {

        if (patient.getId() == null || patient.getId().equals("")) {

            return create(patient);

        } else {
            return update(patient);
        }

    }

    @Override
    public Patient checkPatient(String userName) {
        String sql = "select * from covid19.patients where email = ?";

        List<Patient> patients = jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper(Patient.class));
        return patients.size() > 0 ? patients.get(0) : null;
    }
    @Override
    public Patient update(Patient patient) {
        jdbcTemplate.update(
                "UPDATE covid19.patients SET documentNumber = ?,fullName = ?,direction = ?,phone = ?,email = ?,idDocumentType = ?,idTown=?,idState=?,birthDate=? WHERE id = ?",
                patient.getDocumentNumber(), patient.getFullName(), patient.getDirection(), patient.getPhone(), patient.getEmail(), patient.getDocumentType().getId(), patient.getTown().getId(), patient.getState().getId(), patient.getBirthDate(), patient.getId());
        return patient;
    }


    private Patient create(Patient patient) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO covid19.patients (documentNumber,fullName,direction,phone,email,idDocumentType,idTown,idState,birthDate,idUser,result,latitude,longitude) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, patient.getDocumentNumber());
                ps.setString(2, patient.getFullName());
                ps.setString(3, patient.getDirection());
                ps.setString(4, patient.getPhone());
                ps.setString(5, patient.getEmail());
                ps.setInt(6, Integer.parseInt(patient.getDocumentType().getId()));
                ps.setInt(7, Integer.parseInt(patient.getTown().getId()));
                ps.setInt(8, Integer.parseInt(patient.getState().getId()));
                ps.setString(9, patient.getBirthDate());
                ps.setInt(10, Integer.parseInt(patient.getUser().getId()));
                ps.setString(11,patient.getResult());
                ps.setString(12,patient.getLatitude());
                ps.setString(13,patient.getLongitude());
                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();
        patient.setId(key.toString());
        return patient;
    }

}
