package com.poli.inventory.controllers;

import com.poli.inventory.domain.Menu;
import com.poli.inventory.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class MenuController {


    @Autowired
    private MenuService menuService;

    @GetMapping("/consultMenus")
    public Menu consultMenus(String idRole) {
        return menuService.consultMenus(idRole);
    }


}