package com.poli.covid19.services.impl;

import com.poli.covid19.domain.AnswerPatient;
import com.poli.covid19.repositories.AnswerPatientRepository;
import com.poli.covid19.services.AnswerPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerPatientServiceImpl implements AnswerPatientService {

    @Autowired
    private AnswerPatientRepository AnswerPatientRepository;



    @Override
    public AnswerPatient setAnswerPatient(AnswerPatient answerPatient) {
        return AnswerPatientRepository.setAnswerPatient(answerPatient);
    }

}
