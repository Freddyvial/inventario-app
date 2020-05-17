package com.poli.covid19.repositories;

import com.poli.covid19.domain.Patient;
import com.poli.covid19.domain.User;

import java.util.List;

public interface PatientRepository {

    List<Patient> getPatients(String id);
    Patient createPatients(Patient patient);
    Patient checkPatient(String userName);
    Patient update(Patient patient);

}
