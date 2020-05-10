package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.AnswerPatient;
import com.poli.covid19.repositories.AnswerPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class AnswerPatientRepositoryImpl implements AnswerPatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public   AnswerPatient setAnswerPatient(AnswerPatient answerPatient){
        if(answerPatient.getId()==null||answerPatient.getId().equals("")){
            return create(answerPatient);

        }else {
            return update(answerPatient);

        }
    }



    private AnswerPatient update(AnswerPatient answerPatients) {
        jdbcTemplate.update(
                "UPDATE covid19.answerspatients SET idPatient = ?,result = ? WHERE id = ?",
                answerPatients.getIdPatient(), answerPatients.getResult(),answerPatients.getId());
    return answerPatients;
    }


    private AnswerPatient create(AnswerPatient answerPatient) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO covid19.answerspatients (idPatient,result) values(?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, answerPatient.getIdPatient());
                ps.setString(2, answerPatient.getResult());

                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        answerPatient.setId(key.toString());

        return answerPatient  ;
    }
}
