package com.poli.inventory.domain;

public class SubMenu {
    private int idSubMenu;
    private  String nameSubmenu;
    private  int idMenu;
    private String link;

    public int getIdSubMenu() {
        return idSubMenu;
    }

    public void setIdSubMenu(int idSubMenu) {
        this.idSubMenu = idSubMenu;
    }

    public String getNameSubmenu() {
        return nameSubmenu;
    }

    public void setNameSubmenu(String nameSubmenu) {
        this.nameSubmenu = nameSubmenu;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
