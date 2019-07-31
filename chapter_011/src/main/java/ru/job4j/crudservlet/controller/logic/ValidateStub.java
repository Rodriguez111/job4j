package ru.job4j.crudservlet.controller.logic;

import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements ValidatorWithRole {
    private final Map<Integer, AdvancedUser> store = new HashMap<>();
    private int ids = 0;

    @Override
    public boolean add(HttpServletRequest request) {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String role = request.getParameter("role");
        String password = request.getParameter("password");

        AdvancedUser advancedUser = new AdvancedUser(name, login, role, password, "", "");
        advancedUser.setId(this.ids++);
        this.store.put(advancedUser.getId(), advancedUser);
        return true;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        store.computeIfPresent(id, (i, u) -> {
            u.setName(name);
            return u;
        });
        return true;
    }

    @Override
    public boolean delete(HttpServletRequest request) {
        boolean result = false;
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        AdvancedUser advancedUser = store.remove(id);
        if (advancedUser.getName().equals(name)) {
            result = true;
        }
        return result;
    }


    @Override
    public boolean nonNullCheck(String field) {
        return false;
    }

    @Override
    public String formatDate() {
        return null;
    }

    @Override
    public List<AdvancedUser> findAllAdvUsers() {
        return new ArrayList<>(this.store.values());
    }

    @Override
    public AdvancedUser findAdvUserById(int id) {
        return store.get(id);
    }

    @Override
    public String isCredentialWithRole(String login, String password) {
        String role = "";
        for (Map.Entry<Integer, AdvancedUser> entry : store.entrySet()) {
            if (entry.getValue().getLogin().equals(login) && entry.getValue().getPassword().equals(password)) {
                role = entry.getValue().getRole();
                break;
            }
        }
        return role;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean isCredential(String login, String password) {
        return false;
    }
}
