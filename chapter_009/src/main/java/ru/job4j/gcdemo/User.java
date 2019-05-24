package ru.job4j.gcdemo;

public class User {
    private String name;
    private String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User() {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Collected by GC");

    }


}
