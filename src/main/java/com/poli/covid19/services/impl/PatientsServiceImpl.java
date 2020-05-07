package com.poli.covid19.services.impl;

import com.poli.covid19.domain.Patient;
import com.poli.covid19.repositories.PatientRepository;
import com.poli.covid19.services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PatientsServiceImpl implements PatientsService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getPatients(String id) {

        return patientRepository.getPatients(id);
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.createPatients(patient);
    }

}
