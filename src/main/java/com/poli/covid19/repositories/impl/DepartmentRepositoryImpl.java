package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.Department;
import com.poli.covid19.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Department> getDepartment() {
        String sql = "";

            sql = "select * from covid19.departments";


        List<Department> departments = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Department.class));
        return departments;
    }


}
