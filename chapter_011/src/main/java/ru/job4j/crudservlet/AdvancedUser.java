package ru.job4j.crudservlet;

public class AdvancedUser extends User {

    private String role;

    public AdvancedUser(String name, String login, String role, String password, String email, String createDate) {
        super(name, login, password, email, createDate);
        this.role = role;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
