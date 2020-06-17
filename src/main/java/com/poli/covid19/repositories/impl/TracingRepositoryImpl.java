package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TracingRepositoryImpl implements TracingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DetailTracing> consultDetailTracing(String id) {
        String sql = "SELECT * FROM covid19.detailtracing WHERE idTracing = ?";

        List<DetailTracing> detailTracings = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper(DetailTracing.class));
        return detailTracings;

    }

    @Override
    public int idMedicalByTracing() {
        String sql = "SELECT m.id medical_id, \n" +
                "SUM(CASE \n" +
                "\t WHEN t.id IS NULL THEN 0\n" +
                "             ELSE 1\n" +
                "END) AS total_tracing\n" +
                " FROM covid19.medical as m\n" +
                "LEFT JOIN covid19.tracing as t on m.id = t.idMedical \n" +
                "group by m.id order by total_tracing asc limit 1";
        List idMedical = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            int id = Integer.parseInt(String.format(row.get("medical_id").toString()));
            idMedical.add(id);
        }

        return Integer.parseInt(idMedical.get(0).toString());
    }

    @Override
    public List<Tracing> consultTracing(String id) {
        String sql = "SELECT t.id as idTracing, t.date as dateTracing,t.idPatient, t.idMedical,t.idState as idStateTracing,\n" +
                "p.documentNumber as documentNumberPatient,p.fullName as fullNamePatient, p.direction as directionPatient, p.phone as phonePatient,\n" +
                "p.email as emailPatient, p.idDocumentType as idDocumentTypePatient, p.idTown as idTownPatient, p.idState as idStatePatient, \n" +
                "p.changeDate as changeDatePatient, p.birthDate as birthDatePatient, p.idUser as idUserPatient,p.result,\n" +
                "m.fullName as fullNameMedical, m.numberDocument as numberDocumentMedical, m.email as emailMedical, m.phone as phoneMedical,\n" +
                "m.idDocumentType as idDocumentTypeMedical, m.state as stateMedical, m.idUser as idUserMedical,\n" +
                "st.value as nameStateTracing,\n" +
                "tn.idDepartment,tn.name as nameTown,\n" +
                "dp.name as nameDepartment,\n" +
                "dtp.value as nameDocumentTypePatient,\n" +
                "dtm.value as nameDocumentTypeMedical,\n" +
                "sp.value as nameStatePatient\n" +
                "FROM covid19.tracing as t\n" +
                "left join covid19.patients as p on t.idPatient = p.id\n" +
                "left join covid19.medical as m on t.idMedical = m.id\n" +
                "left join covid19.state as st on t.idState = st.id\n" +
                "inner join covid19.town as tn on p.idTown = tn.id\n" +
                "inner join covid19.departments as dp on tn.idDepartment= dp.id\n" +
                "inner join covid19.documenttype as dtp on p.idDocumentType = dtp.id\n" +
                "inner join covid19.documenttype as dtm on m.idDocumentType = dtm.id\n" +
                "inner join covid19.state as sp on p.idState = sp.id\n" +
                "where t.idMedical=? and t.idState = 4 ";
        List<Tracing> tracings = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{id});
        for (Map row : rows) {
            Tracing newTracing = new Tracing();
            newTracing.setId((String.format(row.get("idTracing").toString())));
            newTracing.setDate(String.format(row.get("dateTracing").toString()));
            Patient patient = new Patient();
            patient.setId(String.format(row.get("idPatient").toString()));
            patient.setDocumentNumber((String) row.get("documentNumberPatient"));
            patient.setFullName((String) row.get("fullNamePatient"));
            patient.setDirection((String) row.get("directionPatient"));
            patient.setPhone((String) row.get("phonePatient"));
            patient.setEmail((String) row.get("emailPatient"));
            DocumentType documentType = new DocumentType();
            documentType.setId(String.format(row.get("idDocumentTypePatient").toString()));
            documentType.setValue((String) row.get("nameDocumentTypePatient"));
            patient.setDocumentType(documentType);
            Town town = new Town();
            town.setId(String.format(row.get("idTownPatient").toString()));
            town.setIdDepartment(String.format(row.get("idDepartment").toString()));
            town.setName((String) row.get("nameTown"));
            patient.setTown(town);
            Department department = new Department();
            department.setName((String) row.get("nameDepartment"));
            patient.setDepartment(department);
            State statePatient = new State();
            statePatient.setId(String.format(row.get("idStatePatient").toString()));
            statePatient.setValue((String) row.get("nameStatePatient"));
            patient.setState(statePatient);
            patient.setChangeDate(String.format(row.get("changeDatePatient").toString()));
            patient.setBirthDate((String) row.get("birthDatePatient"));
            User userPatient = new User();
            userPatient.setId(String.format(row.get("idUserPatient").toString()));
            patient.setUser(userPatient);
            newTracing.setPatient(patient);
            Medical medical = new Medical();
            medical.setId(String.format(row.get("idMedical").toString()));
            medical.setFullName((String) row.get("fullNameMedical"));
            medical.setNumberDocument((String) row.get("numberDocumentMedical"));
            medical.setEmail((String) row.get("emailMedical"));
            medical.setPhone((String) row.get("phoneMedical"));
            DocumentType documentTypeMedical = new DocumentType();
            documentTypeMedical.setId((String.format(row.get("idDocumentTypeMedical").toString())));
            documentTypeMedical.setValue((String) row.get("nameDocumentTypeMedical"));
            medical.setDocumentType(documentTypeMedical);
            medical.setState((String) row.get("stateMedical"));
            User userMedical = new User();
            userMedical.setId(String.format(row.get("idUserMedical").toString()));
            medical.setUser(userMedical);
            newTracing.setMedical(medical);
            State stateTracing = new State();
            stateTracing.setId(String.format(row.get("idStateTracing").toString()));
            stateTracing.setValue((String) row.get("nameStateTracing"));
            newTracing.setState(stateTracing);
            tracings.add(newTracing);
        }
        return tracings;


    }

    @Override
    public DetailTracing createDetailTracing(DetailTracing detailTracing) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO covid19.detailtracing (checkObservation,medication,evolutionPatient, idTracing) values(?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, detailTracing.getCheckObservation());
                ps.setString(2, detailTracing.getMedication());
                ps.setString(3, detailTracing.getEvolutionPatient());
                ps.setString(4, detailTracing.getIdTracing());
                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();

        detailTracing.setId(key.toString());
        return detailTracing;
    }

    @Override
    public Tracing createTracing(Tracing tracing) {
        if (tracing.getId() == null || tracing.getId().equals("")) {
            return create(tracing);

        } else {
            return update(tracing);

        }

    }

    private Tracing update(Tracing tracing) {
        jdbcTemplate.update(
                "UPDATE covid19.tracing SET idPatient = ?,idMedical = ?,idState = ? WHERE id = ?",
                tracing.getPatient().getId(), tracing.getMedical().getId(), tracing.getState().getId(), tracing.getId());
        return tracing;
    }


    private Tracing create(Tracing tracing) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO covid19.tracing (idPatient,idMedical,idState) values(?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tracing.getPatient().getId());
                ps.setString(2, tracing.getMedical().getId());
                ps.setString(3, tracing.getState().getId());


                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();

        tracing.setId(key.toString());

        return tracing;
    }
}
