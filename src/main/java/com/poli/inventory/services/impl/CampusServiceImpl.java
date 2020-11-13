package com.poli.inventory.services.impl;


import com.poli.inventory.domain.Role;
import com.poli.inventory.repositories.RoleRepository;
import com.poli.inventory.services.RoleService;
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
