package com.poli.covid19.services;

import com.poli.covid19.domain.ChartResultByBirthDate;
import com.poli.covid19.domain.ChartResultByDepartment;
import com.poli.covid19.domain.ChartResultByDepartmentByState;
import com.poli.covid19.domain.ChartResultByState;

import java.util.List;

public interface ChartService {
    List<ChartResultByDepartment> consultResultByDepartment();
    List<ChartResultByState> consultResultByStates();
    List<ChartResultByDepartmentByState> consultResultByDepartmentByState();
    List<ChartResultByBirthDate> consultResultByBirthDate();
}
