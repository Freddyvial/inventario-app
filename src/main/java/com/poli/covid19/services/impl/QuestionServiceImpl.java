package com.poli.covid19.services.impl;

import com.poli.covid19.domain.Question;
import com.poli.covid19.repositories.QuestionRepository;
import com.poli.covid19.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestions() {

        return questionRepository.getQuestion();
    }


}
