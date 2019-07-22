package ru.job4j.crudservlet.sql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLManager {
    private final static BasicDataSource SOURCE = new BasicDataSource();

    public SQLManager() {
        initConnection();
        prepareTable();
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

    private void prepareTable() {
        if (!checkTable()) {
            createTable();
            addConstrain();
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

    private void createTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users "
                + "(id serial primary key, "
                + "name character varying(200) NOT NULL, "
                + "login character varying(60) NOT NULL,"
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
}
