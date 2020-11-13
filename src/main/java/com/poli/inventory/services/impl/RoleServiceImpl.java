package com.poli.covid19.services.impl;

import com.poli.covid19.domain.Role;
import com.poli.covid19.repositories.RoleRepository;
import com.poli.covid19.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> consultRole() {

        return roleRepository.consultRole();
    }


}
