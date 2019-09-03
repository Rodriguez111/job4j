package ru.job4j.crudservlet;

import java.util.Objects;

public class User {

    private int id;

    private String name;

    private String login;

    private String password;

    private String email;

    private String role;

    private String country;

    private String city;

    private String createDate;

    public User(String name, String login, String password, String email,
                String role, String country, String city, String createDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.country = country;
        this.city = city;
        this.createDate = createDate;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


}
