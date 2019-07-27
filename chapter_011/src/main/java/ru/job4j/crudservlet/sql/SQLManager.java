package ru.job4j.crudservlet.sql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SQLManager {
    private final static BasicDataSource SOURCE = new BasicDataSource();

    public SQLManager() {
        initConnection();
        prepareDataStructure();
    }

    private static void initConnection() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void prepareDataStructure() {
        if (!checkTable()) {
            createRolesTable();
            createUsersTable();
            addConstrain();
            createDefaultRoles();
            createDefaultUser();
        }
    }

    private boolean checkTable() {
        String query = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='users'";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        return queryManager.runQuery(query, params, ps -> {
            boolean result = false;
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            return result;
        }).orElse(false);
    }

    private void createRolesTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS roles "
                + "(id serial primary key, "
                + "role character varying(60) UNIQUE NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users "
                + "(id serial primary key, "
                + "name character varying(200) NOT NULL, "
                + "RoleId INTEGER REFERENCES roles (id) NOT NULL,"
                + "login character varying(60) NOT NULL,"
                + "password character varying(16) NOT NULL,"
                + "email character varying(120) NOT NULL,"
                + "create_date character varying(19) NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void addConstrain() {
        String createTable = "ALTER TABLE users "
                + "ADD CONSTRAINT unique_fields UNIQUE (name, login, email)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createDefaultRoles() {
        String createTable = "INSERT INTO roles "
                + "(role) "
                + "VALUES ('administrator'), ('user')";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createDefaultUser() {
        Date rawDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        String currentDate = dateFormat.format(rawDate);
        System.out.println(currentDate);
        String insertUser = "INSERT INTO users "
                + "(name, RoleId, login, password, email, create_date) "
                + "VALUES ('admin', (SELECT id FROM roles WHERE role = 'administrator'), 'admin', 'admin', 'admin@mail.com', '"+ currentDate + "')";
        System.out.println(insertUser);
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(insertUser, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

}
