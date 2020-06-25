package com.poli.covid19.repositories;

import com.poli.covid19.domain.ChartResultByBirthDate;
import com.poli.covid19.domain.ChartResultByDepartment;
import com.poli.covid19.domain.ChartResultByDepartmentByState;
import com.poli.covid19.domain.ChartResultByState;

import java.util.List;

public interface ChartRepository {
    List<ChartResultByDepartment> consultResultByDepartment();
    List<ChartResultByState> consultResultByState();
    List<ChartResultByDepartmentByState> consultResultByDepartmentByState();
    List<ChartResultByBirthDate> consultResultByBirthDate();
}

