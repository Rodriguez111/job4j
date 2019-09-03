package ru.job4j.bootstrap;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("isMale")
    private boolean isMale;
    @JsonProperty("description")
    private String description;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;


    public User() {
    }

    public User(String name, String surname, boolean isMale, String description) {
        this.name = name;
        this.surname = surname;
        this.isMale = isMale;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
