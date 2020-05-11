package com.poli.covid19.services.impl;


import com.poli.covid19.domain.Town;
import com.poli.covid19.repositories.TownRepository;
import com.poli.covid19.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    @Autowired
    private TownRepository townRepository;

    @Override
    public List<Town> getTownByDepartment(String id) {

        return townRepository.getTownByDepartment(id);
    }


}
