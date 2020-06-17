package com.poli.covid19.services.impl;

import com.poli.covid19.domain.*;
import com.poli.covid19.repositories.PatientRepository;
import com.poli.covid19.repositories.TracingRepository;
import com.poli.covid19.repositories.UserRepository;
import com.poli.covid19.services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PatientsServiceImpl implements PatientsService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TracingRepository tracingRepository;
    @Override
    public List<Patient> getPatients(String id) {

        return patientRepository.getPatients(id);
    }

    @Override
    public Patient createPatient(Patient patient) throws Exception {
        Patient patientExist = patientRepository.checkPatient(patient.getEmail());

        if(patientExist == null) {

            User user = new User();
            Role role= new Role();
            role.setId("1");
            user.setUserName(patient.getEmail());
            user.setPassWord(patient.getDocumentNumber());
            user.setRole(role);
            User newUser = userRepository.createUser(user);
            patient.setUser(newUser);
            Patient patient1=patientRepository.createPatients(patient);
            int idMedical= tracingRepository.idMedicalByTracing();
            Tracing tracing=new Tracing();
            Medical medical=new Medical();
            medical.setId(String.valueOf(idMedical));
            tracing.setMedical(medical);
            tracing.setPatient(patient1);
            State state=new State();
            state.setId(patient.getState().getId());
            tracing.setState(state);
            tracingRepository.createTracing(tracing);
            return patient1;
        } else {
          throw  new Exception("Usuario ya existe");

        }
    }

}
