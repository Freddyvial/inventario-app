package com.poli.covid19.services.impl;

import com.poli.covid19.domain.Department;
import com.poli.covid19.repositories.DepartmentRepository;
import com.poli.covid19.services.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartment() {

        return departmentRepository.getDepartment();
    }


}
