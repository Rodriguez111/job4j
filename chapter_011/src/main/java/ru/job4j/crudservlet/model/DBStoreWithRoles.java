package ru.job4j.crudservlet.model;

import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.sql.Consumer;
import ru.job4j.crudservlet.sql.QueryManager;
import ru.job4j.crudservlet.sql.SQLManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBStoreWithRoles implements Store, RolesStore {

    private final static DBStoreWithRoles INSTANCE = new DBStoreWithRoles();
    private final static Store simpleStore = DBStore.getInstance();
    private final static SQLManager SQL_MANAGER = new SQLManager();

    private DBStoreWithRoles() {
    }

    public static DBStoreWithRoles getInstance() {
        return INSTANCE;
    }


    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean addAdvUser(AdvancedUser advancedUser) {
        String query = "INSERT INTO users "
                + "(name, roleid, login, password, email, create_date) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        int roleId = findIdByRole(advancedUser.getRole());
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(advancedUser.getName(), roleId, advancedUser.getLogin(),
                advancedUser.getPassword(), advancedUser.getEmail(), advancedUser.getCreateDate());
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
    }

    @Override
    public void updateAdvUser(int id, AdvancedUser advancedUser) {
        String query = "UPDATE users SET name=?, RoleId=?, login=?, password=?, email=? WHERE id=?";
        int roleId = findIdByRole(advancedUser.getRole());
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(advancedUser.getName(), roleId, advancedUser.getLogin(),
                advancedUser.getPassword(), advancedUser.getEmail(), id);
        queryManager.runQuery(query, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
    }

    @Override
    public boolean delete(User user) {
        return simpleStore.delete(user);
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
                String role = findRoleById(resultSet.getInt("RoleId"));
                AdvancedUser user = new AdvancedUser(resultSet.getString("name"), resultSet.getString("login"),
                        role, resultSet.getString("password"), resultSet.getString("email"),
                        resultSet.getString("create_date"));
                user.setId(resultSet.getInt("id"));
                resultList.add(user);
            }
        });
        return resultList;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public String findRoleById(int roleId) {
        String query = "SELECT * FROM roles WHERE id = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(roleId);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            String resultRole = null;
            if (resultSet.next()) {
                resultRole = resultSet.getString("role");
            }
            return resultRole;
        }).orElse(null);
    }

    @Override
    public int findIdByRole(String role) {
        String query = "SELECT id FROM roles WHERE role = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(role);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            int resultRole = -1;
            if (resultSet.next()) {
                resultRole = resultSet.getInt("id");
            }
            return resultRole;
        }).orElse(-1);
    }

    @Override
    public boolean userExists(String login) {
        String query = "SELECT id FROM users WHERE login = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(login);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            boolean result = false;
            if (resultSet.next()) {
                result = true;
            }
            return result;
        }).orElse(false);
    }

    @Override
    public AdvancedUser findAdvUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(id);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            AdvancedUser resultUser = null;
            if (resultSet.next()) {
                String role = findRoleById(resultSet.getInt("RoleId"));
                resultUser = new AdvancedUser(resultSet.getString("name"), resultSet.getString("login"),
                        role, resultSet.getString("password"), resultSet.getString("email"),
                        resultSet.getString("create_date"));
                resultUser.setId(resultSet.getInt("id"));
            }
            return resultUser;
        }).orElse(null);
    }
}
