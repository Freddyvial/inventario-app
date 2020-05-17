package com.poli.covid19.services;



import com.poli.covid19.domain.Medical;

import java.util.List;

public interface MedicalService {

    List<Medical> getMedical();
    Medical createMedical (Medical medical) throws Exception;
}
