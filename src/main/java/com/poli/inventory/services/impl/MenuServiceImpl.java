package com.poli.inventory.services.impl;

import com.poli.inventory.domain.Menu;
import com.poli.inventory.repositories.MenuRepository;
import com.poli.inventory.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu consultMenus(String idRole) {

        return menuRepository.consultMenus(idRole);
    }


}
