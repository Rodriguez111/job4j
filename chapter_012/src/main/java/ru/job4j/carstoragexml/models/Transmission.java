package ru.job4j.carstoragexml.models;

public class Transmission {
    private int id;
    private String transmissionType;

    public Transmission() {
    }

    public Transmission(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
