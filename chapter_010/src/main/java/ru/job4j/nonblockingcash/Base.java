package ru.job4j.nonblockingcash;

public class Base {
    private int id;
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
