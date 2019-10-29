package ru.job4j.diffstorages145772;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JDBCConnection {
    private final static BasicDataSource SOURCE = new BasicDataSource();

    public JDBCConnection() {
        initConnection();
        prepareTables();
    }

    private void initConnection() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/diff_storages");
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


    private void prepareTables() {
        if (!checkTable()) {
            createUsersTable();
        }

    }

    private boolean checkTable() {
        String query = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE table_catalog='diff_storages' AND TABLE_NAME='users'";
        Connection connection = getConnection();
        PreparedStatement ps;
        boolean result = false;
        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void createUsersTable() {
        String createTable = "CREATE TABLE users "
                + "(id serial primary key, "
                + "login character varying(30) UNIQUE NOT NULL,"
                + "surname character varying(30) NOT NULL,"
                + "name character varying(30) NOT NULL)";
        Connection connection = getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(createTable);
            ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
