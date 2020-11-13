package com.poli.inventory.services.impl;

import com.poli.inventory.domain.Campus;
import com.poli.inventory.repositories.CampusRepository;
import com.poli.inventory.services.CampusService;
import com.poli.inventory.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusServiceImpl implements CampusService {

    @Autowired
    private CampusRepository campusRepository;

    @Override
    public List<Campus> consultCampus() {

        return campusRepository.consultCampus();
    }


}
