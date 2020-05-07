package com.poli.covid19.repositories;

import com.poli.covid19.domain.Patient;

import java.util.List;

public interface PatientRepository {

    List<Patient> getPatients(String id);
    Patient createPatients(Patient patient);
}
