package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.Question;
import com.poli.covid19.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Question> getQuestion() {
        String sql = "select * from covid19.questions";

        List<Question> questions = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Question.class));
        return questions;
    }


}
