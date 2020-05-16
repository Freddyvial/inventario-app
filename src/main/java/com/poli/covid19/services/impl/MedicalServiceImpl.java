package com.poli.covid19.services.impl;



import com.poli.covid19.domain.Medical;
import com.poli.covid19.repositories.MedicalRepository;
import com.poli.covid19.services.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalServiceImpl implements MedicalService {

    @Autowired
    private com.poli.covid19.repositories.MedicalRepository MedicalRepository;

    @Override
    public List<Medical> getMedical() {

        return MedicalRepository.getMedical();
    }

    @Override
    public Medical createMedical(Medical medical) {
        return MedicalRepository.createMedical(medical);
    }

}
