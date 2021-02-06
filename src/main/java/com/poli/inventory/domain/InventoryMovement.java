package com.poli.inventory.domain;

import java.util.Date;

public class InventoryMovement {
    private int idInvMov;
    private TypeInvMov typeInvMov;
    private String detail;
    private User user;
    private Date date;

    public int getIdInvMov() {
        return idInvMov;
    }

    public void setIdInvMov(int idInvMov) {
        this.idInvMov = idInvMov;
    }

    public TypeInvMov getTypeInvMov() {
        return typeInvMov;
    }

    public void setTypeInvMov(TypeInvMov typeInvMov) {
        this.typeInvMov = typeInvMov;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
