package ru.job4j.carstoragexml.models;

public class Engine {
    private int id;
    private String engineType;

    public Engine() {
    }

    public Engine(String engineType) {
        this.engineType = engineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}
