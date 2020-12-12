package com.poli.inventory.repositories.impl;

import com.poli.inventory.domain.Role;
import com.poli.inventory.repositories.RoleRepository;
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
        String sql = "select * from u280625412_inventory.role";
        List<Role> roles = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class));
        return roles;
    }


}
