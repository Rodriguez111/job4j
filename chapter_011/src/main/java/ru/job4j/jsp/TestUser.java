package ru.job4j.jsp;

public class TestUser {
    private String login;
    private String email;

    public TestUser(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
