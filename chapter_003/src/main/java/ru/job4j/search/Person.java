package ru.job4j.search;

public class Person {
    private String name;
    private String sirname;
    private String phone;
    private String address;

    public Person(String name, String sirname, String phone, String address) {
        this.name = name;
        this.sirname = sirname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSirname() {
        return sirname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
