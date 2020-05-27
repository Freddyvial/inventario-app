package com.poli.covid19.repositories.impl;

import com.poli.covid19.domain.Role;
import com.poli.covid19.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> consultRole() {
        String sql = "select * from covid19.role";
        List<Role> roles = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class));
        return roles;
    }


}
