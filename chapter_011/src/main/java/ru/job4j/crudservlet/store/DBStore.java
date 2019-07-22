package ru.job4j.crudservlet.store;

import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.sql.Consumer;
import ru.job4j.crudservlet.sql.QueryManager;
import ru.job4j.crudservlet.sql.SQLManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {
    private final static DBStore INSTANCE = new DBStore();
    private final static SQLManager SQL_MANAGER = new SQLManager();

    private DBStore() {
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        String query = "INSERT INTO users "
                + "(name, login, email, create_date) "
                + "VALUES (?, ?, ?, ?);";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate());
        return queryManager.runQuery(query, params, ps -> {
            boolean success = false;
            int row = ps.executeUpdate();
            if (row > 0) {
                success = true;
            }
            return success;
        }).orElse(false);
    }

    @Override
    public void update(int id, User user) {
        String query = "UPDATE users SET name =?, login=?, email=? WHERE id=?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(user.getName(), user.getLogin(), user.getEmail(), id);
        queryManager.runQuery(query, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
    }

    @Override
    public boolean delete(User user) {
        String query = "DELETE FROM users WHERE id=?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(user.getId());

        return queryManager.runQuery(query, params, ps -> {
            boolean success = false;
            int row = ps.executeUpdate();
            if (row == 1) {
                success = true;
            }
            return success;
        }).orElse(false);
    }


    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY create_date";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("login"),
                        resultSet.getString("email"), resultSet.getString("create_date"));
                user.setId(resultSet.getInt("id"));
                resultList.add(user);
            }
        });
        return resultList;
    }

    @Override
    public User findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(id);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            User resultUser = null;
            if (resultSet.next()) {
                resultUser = new User(resultSet.getString("name"), resultSet.getString("login"),
                        resultSet.getString("email"), resultSet.getString("create_date"));
                resultUser.setId(resultSet.getInt("id"));
            }
            return resultUser;
        }).orElse(null);
    }
}
