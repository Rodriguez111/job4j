package ru.job4j.crudservlet;

import java.util.Objects;

public class User {
    private static int count;
    private int id;

    private String name;

    private String login;

    private String email;

    private String createDate;

    public User(String name, String login, String email, String createDate) {
        this.id = incrementCount();
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    private synchronized int incrementCount() {
       return this.id = count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                login.equals(user.login) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email);
    }
}
