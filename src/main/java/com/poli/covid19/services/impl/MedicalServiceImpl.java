package com.poli.covid19.services.impl;



import com.poli.covid19.domain.Medical;
import com.poli.covid19.domain.Role;
import com.poli.covid19.domain.User;
import com.poli.covid19.repositories.MedicalRepository;
import com.poli.covid19.repositories.UserRepository;
import com.poli.covid19.services.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalServiceImpl implements MedicalService {

    @Autowired
    private com.poli.covid19.repositories.MedicalRepository medicalRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Medical> getMedical() {

        return medicalRepository.getMedical();
    }

    @Override
    public Medical createMedical(Medical medical) throws Exception {
        Medical medicalExist = medicalRepository.checkMedical(medical.getEmail());
        if(medicalExist==null){
            User user= new User();
            Role role= new Role();
            role.setId("2");
            user.setUserName(medical.getEmail());
            user.setPassWord(medical.getNumberDocument());
            user.setRole(role);
            User newUser= userRepository.createUser(user);
            medical.setUser(newUser);
            return medicalRepository.createMedical(medical);
        }else {
            return medicalRepository.update(medical);



        }


    }

    @Override
    public Medical checkMedical(String userName) {
        return medicalRepository.checkMedical(userName);
    }

}
