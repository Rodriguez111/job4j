package ru.job4j.crudservlet.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.sql.Consumer;
import ru.job4j.crudservlet.sql.QueryManager;
import ru.job4j.crudservlet.sql.SQLManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(DBStore.class);
    private final static DBStore INSTANCE = new DBStore();
    private final static SQLManager SQL_MANAGER = new SQLManager();

    private DBStore() {
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        LOG.info("Enter method");
        String query = "INSERT INTO users "
                + "(name, login, password, email, countryId, cityId, roleId, create_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        int countryId = findIdByField(user.getCountry(), "country", "countries");
        int cityId = findIdByField(user.getCity(), "city", "cities");
        int roleId = findIdByField(user.getRole(), "role", "roles");
        List<Object> params = List.of(user.getName(), user.getLogin(), user.getPassword(),
                user.getEmail(), countryId, cityId, roleId, user.getCreateDate());
        return queryManager.runQuery(query, params, ps -> {
            boolean success = false;
            int row = ps.executeUpdate();
            if (row > 0) {
                success = true;
            }
            LOG.info("Exit method");
            return success;
        }).orElse(false);
    }

    @Override
    public void update(int id, User user) {
        LOG.info("Enter method");
        String query = "UPDATE users SET name =?, login=?, password=?, email=?, countryId=?, cityId=?, roleId=? WHERE id=?";
        int countryId = findIdByField(user.getCountry(), "country", "countries");
        int cityId = findIdByField(user.getCity(), "city", "cities");
        int roleId = findIdByField(user.getRole(), "role", "roles");
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(user.getName(), user.getLogin(), user.getPassword(), user.getEmail(),
                countryId, cityId, roleId, id);
        queryManager.runQuery(query, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
        LOG.info("Exit method");
    }

    @Override
    public boolean delete(User user) {
        LOG.info("Enter method");
        String query = "DELETE FROM users WHERE id=?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(user.getId());

        return queryManager.runQuery(query, params, ps -> {
            boolean success = false;
            int row = ps.executeUpdate();
            if (row == 1) {
                success = true;
            }
            LOG.info("Exit method");
            return success;
        }).orElse(false);
    }

    @Override
    public List<User> getAllUsers() {
        LOG.info("Enter method");
        List<User> resultList = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY create_date";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                resultList.add(getUserFromResultSet(resultSet));
            }
        });
        LOG.info("Exit method");
        return resultList;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        LOG.info("Enter method");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String role = findFieldById(resultSet.getInt("roleId"), "role", "roles");
        String country = findFieldById(resultSet.getInt("countryId"), "country", "countries");
        String city = findFieldById(resultSet.getInt("cityId"), "city", "cities");
        String createDate = resultSet.getString("create_date");
        User user = new User(name, login, password, email, role, country, city, createDate);
        user.setId(resultSet.getInt("id"));
        LOG.info("Exit method");
        return user;
    }

    @Override
    public User findById(int id) {
        LOG.info("Enter method");
        String query = "SELECT * FROM users WHERE id = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(id);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            User resultUser = null;
            if (resultSet.next()) {
                resultUser = getUserFromResultSet(resultSet);
            }
            LOG.info("Exit method");
            return resultUser;
        }).orElse(null);
    }

    private String findFieldById(int id, String fieldName, String tableName) {
        LOG.info("Enter method");
        String query = "SELECT " + fieldName + " FROM  " + tableName + "  WHERE id = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(id);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            String result = "";
            if (resultSet.next()) {
                result = resultSet.getString(fieldName);
            }
            LOG.info("Exit method");
            return result;
        }).orElse("");
    }

    public int findIdByField(String fieldValue, String fieldName, String tableName) {
        LOG.info("Enter method");
        String query = "SELECT id FROM  " + tableName + "  WHERE " + fieldName + " = ?";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of(fieldValue);
        return queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            int result = -1;
            if (resultSet.next()) {
                result = resultSet.getInt("id");
            }
            LOG.info("Exit method");
            return result;
        }).orElse(-1);
    }

    @Override
    public List<String> getListOfValuesByField(String criteriaField, Object criteriaValue, String tableName, String searchField) {
       LOG.info("Enter method");
       String query = "SELECT " + searchField + " FROM  " + tableName + "  WHERE " + criteriaField + " = ?";
       QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
       List<Object> params = List.of(criteriaValue);
       List<String> result = new ArrayList<>();
       queryManager.runQuery(query, params, ps -> {
           ResultSet resultSet = ps.executeQuery();
           while (resultSet.next()) {
               result.add(resultSet.getString(searchField));
           }
           LOG.info("Exit method");

       });
      return  result;
   }

    @Override
    public List<String> getAllRoles() {
        LOG.info("Enter method");
        String query = "SELECT role FROM  roles";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of();
        List<String> result = new ArrayList<>();
        queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString("role"));
            }
            LOG.info("Exit method");
        });
        return  result;
    }

    @Override
    public List<String> getAllCountries() {
        LOG.info("Enter method");
        String query = "SELECT country FROM  countries";
        QueryManager queryManager = new QueryManager(SQL_MANAGER.getConnection());
        List<Object> params = List.of();
        List<String> result = new ArrayList<>();
        queryManager.runQuery(query, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString("country"));
            }
            LOG.info("Exit method");
        });
        return  result;
    }
}