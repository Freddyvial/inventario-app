package com.poli.covid19.repositories;


import com.poli.covid19.domain.Question;

import java.util.List;

public interface QuestionRepository {

    List<Question> getQuestion();

}
