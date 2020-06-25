package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MedicalRepositoryImpl implements MedicalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Medical checkMedical(String userName) {
        String sql = "select * from covid19.medical where email = ?";

        List<Medical> medicals = jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper(Medical.class));
        return medicals.size() > 0 ? medicals.get(0) : null;
    }


    @Override
    public List<Medical> getMedical() {
        String sql = "select m.*,dt.value as nameDocumentType,s.value as nameState,\n" +
                "u.userName,u.idRole,r.name as nameRole from covid19.medical as m\n" +
                "inner join covid19.documenttype as dt on m.idDocumentType=dt.id\n" +
                "inner join covid19.state as s on m.idState = s.id\n" +
                "inner join covid19.users as u on m.idUser=u.id\n" +
                "inner join covid19.role as r on u.idRole=r.id";
        List<Medical> medicals = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Medical newMedical = new Medical();
            newMedical.setId(String.format(row.get("id").toString()));
            newMedical.setNumberDocument((String) row.get("numberDocument"));
            newMedical.setFullName((String) row.get("fullName"));
            newMedical.setEmail((String) row.get("email"));
            newMedical.setPhone((String) row.get("phone"));
            DocumentType documentType = new DocumentType();
            documentType.setId(String.format(row.get("idDocumentType").toString()));
            documentType.setValue((String) row.get("nameDocumentType"));
            newMedical.setDocumentType(documentType);
            State stateMedical =new State();
            stateMedical.setId(String.format(row.get("idState").toString()));
            stateMedical.setValue((String) row.get("nameState"));
            newMedical.setState(stateMedical);
            User userMedical = new User();
            userMedical.setId(String.format(row.get("idUser").toString()));
            userMedical.setUserName((String) row.get("userName"));
            Role roleUser = new Role();
            roleUser.setId(String.format(row.get("idRole").toString()));
            roleUser.setName((String) row.get("nameRole"));
            userMedical.setRole(roleUser);
            newMedical.setUser(userMedical);
            medicals.add(newMedical);
        }
        return medicals;
    }

    @Override
    public Medical createMedical(Medical medical) {
        if (medical.getId() == null || medical.getId().equals("")) {
            return create(medical);

        } else {
            return update(medical);

        }

    }
    @Override
    public Medical update(Medical medical) {
        jdbcTemplate.update(
                "UPDATE covid19.medical SET fullName = ?,numberDocument = ?,email = ?, phone = ?, idDocumentType=?, state=?  WHERE id = ?",
                medical.getFullName(), medical.getNumberDocument(), medical.getEmail(), medical.getPhone(), medical.getDocumentType().getId(),medical.getState(),medical.getId());
        return medical;
    }


    private Medical create(Medical medical) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO covid19.medical (fullName,numberDocument,email,phone,idDocumentType,state,idUser) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, medical.getFullName());
                ps.setString(2, medical.getNumberDocument());
                ps.setString(3, medical.getEmail());
                ps.setString(4, medical.getPhone());
                ps.setString(5, medical.getDocumentType().getId());
                ps.setString(6, medical.getState().getId());
                ps.setInt(7, Integer.parseInt(medical.getUser().getId()));
                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();

        medical.setId(key.toString());

        return medical;
    }
}
