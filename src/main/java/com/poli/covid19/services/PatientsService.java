package com.poli.covid19.services;

import com.poli.covid19.domain.Patient;

import java.util.List;

public interface PatientsService {

    List<Patient> getPatients(String id);
    Patient createPatient(Patient patient) throws Exception;
}
