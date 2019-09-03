package ru.job4j.crudservlet.model;

import ru.job4j.crudservlet.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStore implements Store {
    private final CopyOnWriteArrayList<User> listOfUsers = new CopyOnWriteArrayList<>();


    private final static UserStore INSTANCE = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        user.setId(generateId());
       return listOfUsers.addIfAbsent(user);
    }

    @Override
    public void update(int id, User user) {
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (user.equals(listOfUsers.get(i))) {
                listOfUsers.remove(i);
                listOfUsers.add(user);
                break;
            }
        }
    }

    @Override
    public boolean delete(User user) {
        return listOfUsers.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.listOfUsers;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (id == listOfUsers.get(i).getId()) {
                result = listOfUsers.get(i);
                break;
            }
        }
        return result;
    }

    private int generateId() {
        int result = 0;
        if (listOfUsers.size() > 0) {
            result =  listOfUsers.get(listOfUsers.size() - 1).getId() + 1;
        }
        return result;
    }

    @Override
    public int findIdByField(String fieldValue, String fieldName, String tableName) {
        return 0;
    }

    @Override
    public List<String> getListOfValuesByField(String criteriaField, Object criteriaValue, String tableName, String searchField) {
        return null;
    }

    @Override
    public List<String> getAllRoles() {
        return null;
    }

    @Override
    public List<String> getAllCountries() {
        return null;
    }
}