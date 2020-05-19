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
    public List<Tracing> consultTracing() {
        String sql ="SELECT t.id as idTracing, t.date as dateTracing,t.idPatient, t.idMedical, t.observations as observationsTracing,t.idState as idStateTracing,\n" +
                "p.documentNumber as documentNumberPatient,p.fullName as fullNamePatient, p.direction as directionPatient, p.phone as phonePatient,\n" +
                "p.email as emailPatient, p.idDocumentType as idDocumentTypePatient, p.idTown as idTownPatient, p.idState as idStatePatient, \n" +
                "p.changeDate as changeDatePatient, p.birthDate as birthDatePatient, p.idUser as idUserPatient,\n" +
                "m.fullName as fullNameMedical, m.numberDocument as numberDocumentMedical, m.email as emailMedical, m.phone as phoneMedical,\n" +
                "m.idDocumentType as idDocumentTypeMedical, m.state as stateMedical, m.idUser as idUserMedical,\n" +
                "s.value as valuePatient,\n" +
                "d.id as idDetailTracing, d.date as dateDetailTracing, d.checkObservation as checkObservationDetail, d.medication as medicationDetailTracing,\n" +
                "d.evolutionPatient as evolutionPatientDetail\n" +
                "FROM covid19.tracing as t\n" +
                "inner join covid19.patients as p on t.idPatient = p.id\n" +
                "inner join covid19.medical as m on t.idMedical = m.id\n" +
                "inner join covid19.state as s on t.idState = s.id\n" +
                "inner join covid19.detailtracing as d on t.id = d.idTracing;";
                List<Tracing> tracings =new ArrayList<>();
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);
                for (Map row : rows){
                    Tracing newTracing =new Tracing();
                    newTracing.setId((String.format(row.get("idTracing").toString())));
                    newTracing.setDate(String.format(row.get("dateTracing").toString()));
                    Patient patient = new Patient();
                    patient.setId(String.format(row.get("idPatient").toString()));
                    patient.setDocumentNumber((String) row.get("documentNumberPatient"));
                    patient.setFullName((String) row.get("fullNamePatient"));
                    patient.setDirection((String) row.get("directionPatient"));
                    patient.setPhone((String) row.get("phonePatient"));
                    patient.setEmail((String) row.get("emailPatient"));
                    DocumentType documentType= new DocumentType();
                    documentType.setId(String.format(row.get("idDocumentTypePatient").toString()));
                    patient.setDocumentType(documentType);
                    Town town=new Town();
                    town.setId(String.format(row.get("idTownPatient").toString()));
                    patient.setTown(town);
                    State statePatient= new State();
                    statePatient.setId(String.format(row.get("idStatePatient").toString()));
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
                    DocumentType documentTypeMedical =new DocumentType();
                    documentTypeMedical.setId((String.format(row.get("idDocumentTypeMedical").toString())));
                    medical.setDocumentType(documentTypeMedical);
                    medical.setState((String) row.get("stateMedical"));
                    User userMedical =new User();
                    userMedical.setId(String.format(row.get("idUserMedical").toString()));
                    medical.setUser(userMedical);
                    newTracing.setMedical(medical);
                    State stateTracing=new State();
                    stateTracing.setId(String.format(row.get("idStateTracing").toString()));
                    stateTracing.setValue((String) row.get("valuePatient"));
                    newTracing.setState(stateTracing);
                    DetailTracing detailTracing = new DetailTracing();
                    detailTracing.setId(String.format(row.get("idDetailTracing").toString()));
                    detailTracing.setDate(String.format(row.get("dateDetailTracing").toString()));
                    detailTracing.setCheckObservation((String) row.get("checkObservationDetail"));
                    detailTracing.setMedication((String) row.get("medicationDetailTracing"));
                    detailTracing.setEvolutionPatient((String) row.get("evolutionPatientDetail"));
                    detailTracing.setTracing(String.format(row.get("idTracing").toString()));
                    newTracing.setDetailTracing(detailTracing);
                    newTracing.setObservation((String)row.get("observationsTracing"));
                    tracings.add(newTracing);
                }
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
