package com.poli.inventory.domain;

public class Article {
    private int id;
    private String name;
    private String serial;
    private byte[] photo;
    private State state;
    private Room room;
    private  TypeArticle TypeArticle;
    private Campus campus;

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public com.poli.inventory.domain.TypeArticle getTypeArticle() {
        return TypeArticle;
    }

    public void setTypeArticle(com.poli.inventory.domain.TypeArticle typeArticle) {
        TypeArticle = typeArticle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
