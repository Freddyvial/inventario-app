package com.poli.covid19.services.impl;

import com.poli.covid19.domain.Patient;
import com.poli.covid19.domain.Role;
import com.poli.covid19.domain.User;
import com.poli.covid19.repositories.PatientRepository;
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
            return patientRepository.createPatients(patient);
        } else {
          return  patientRepository.update(patient);

        }
    }

}
