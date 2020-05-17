package com.poli.covid19.repositories;

import com.poli.covid19.domain.Medical;


import java.util.List;

public interface MedicalRepository {
    Medical checkMedical(String userName);
    List<Medical> getMedical();
    Medical createMedical(Medical medical);
    Medical update(Medical medical);
}
