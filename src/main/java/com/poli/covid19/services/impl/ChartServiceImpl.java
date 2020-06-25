package com.poli.covid19.services.impl;

import com.poli.covid19.domain.ChartResultByBirthDate;
import com.poli.covid19.domain.ChartResultByDepartment;
import com.poli.covid19.domain.ChartResultByDepartmentByState;
import com.poli.covid19.domain.ChartResultByState;
import com.poli.covid19.repositories.ChartRepository;
import com.poli.covid19.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    private ChartRepository chartRepository;

    @Override
    public List<ChartResultByDepartment> consultResultByDepartment() {

        return chartRepository.consultResultByDepartment();
    }
    @Override
    public List<ChartResultByState> consultResultByStates() {

        return chartRepository.consultResultByState();
    }
    @Override
    public List<ChartResultByDepartmentByState> consultResultByDepartmentByState() {

        return chartRepository.consultResultByDepartmentByState();
    }
    @Override
    public List<ChartResultByBirthDate> consultResultByBirthDate() {

        return chartRepository.consultResultByBirthDate();
    }

}
