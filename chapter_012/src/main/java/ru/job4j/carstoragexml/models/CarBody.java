package ru.job4j.carstoragexml.models;

public class CarBody {
    private int id;
    private String bodyType;

    public CarBody() {
    }

    public CarBody(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
