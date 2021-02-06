package com.poli.inventory.domain;

import java.util.Date;

public class GeneralReport {
    private Integer idRoom;
    private  String nameRoom;
    private  byte[] photoRoom;
    private String userName;
    private String nameArticle;
    private String serialArticle;
    private Date dateArticle;
    private  byte[] photoArticle;

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public byte[] getPhotoRoom() {
        return photoRoom;
    }

    public void setPhotoRoom(byte[] photoRoom) {
        this.photoRoom = photoRoom;
    }

    public byte[] getPhotoArticle() {
        return photoArticle;
    }

    public void setPhotoArticle(byte[] photoArticle) {
        this.photoArticle = photoArticle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public String getSerialArticle() {
        return serialArticle;
    }

    public void setSerialArticle(String serialArticle) {
        this.serialArticle = serialArticle;
    }

    public Date getDateArticle() {
        return dateArticle;
    }

    public void setDateArticle(Date dateArticle) {
        this.dateArticle = dateArticle;
    }


}
