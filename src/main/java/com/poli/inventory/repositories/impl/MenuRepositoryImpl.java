package com.poli.inventory.repositories.impl;


import com.poli.inventory.domain.Menu;
import com.poli.inventory.domain.SubMenu;
import com.poli.inventory.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Menu consultMenus(String idRole) {
        String sql = "SELECT m.*,s.idSubMenu,s.nameSubMenu,s.link FROM roominventory.submenus as s\n" +
                "INNER JOIN roominventory.menus as m on s.idMenu=m.idMenu\n" +
                "WHERE m.idRole=?";
        List<Menu> menus = new ArrayList<>();
        List<SubMenu> subMenus = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{idRole});
        for (Map row : rows) {

            SubMenu subMenu = new SubMenu();
            subMenu.setIdSubMenu((int) row.get("idSubMenu"));
            subMenu.setNameSubmenu((String) row.get("nameSubMenu"));
            subMenu.setIdMenu((int) row.get("idMenu"));
            subMenu.setLink((String) row.get("link"));
            subMenus.add(subMenu);
        }
        for (Map row : rows) {
            Menu newMenu = new Menu();
            newMenu.setIdMenu((int) row.get("idMenu"));
            newMenu.setNameMenu((String) row.get("nameMenu"));
            newMenu.setSubMenus(subMenus);
            menus.add(newMenu);
        }

        return menus.size() > 0 ? menus.get(0) : null;
    }


}
