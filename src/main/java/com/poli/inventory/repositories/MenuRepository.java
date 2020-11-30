package com.poli.inventory.repositories;


import com.poli.inventory.domain.Menu;

public interface MenuRepository {
    Menu consultMenus(String idRole);

}
